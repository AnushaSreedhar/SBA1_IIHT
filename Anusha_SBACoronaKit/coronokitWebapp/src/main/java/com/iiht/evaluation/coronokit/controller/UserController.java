package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.OrderSummary;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;
			case "ordersummary":
				viewName = showOrderSummary(request, response);
				break;
			default:
				viewName = "notfound.jsp";
				break;
			}
		} catch (Exception ex) {

			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);

	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		String view = "newuser.jsp";
		return view;
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {
		String view = "user?action=showproducts";
		String usrName = request.getParameter("usrName");
		String usrEmail = request.getParameter("usrEmail");
		String usrMobile = request.getParameter("usrMobile");
		OrderSummary orderSumm;
		if (usrName != null && usrEmail != null || usrMobile != null) {
			HttpSession session = request.getSession();
			if (session.getAttribute("orderSumm") == null) {
				orderSumm = new OrderSummary(new CoronaKit(), new ArrayList<KitDetail>());
				session.setAttribute("orderSumm", orderSumm);
			} else {
				orderSumm = (OrderSummary) session.getAttribute("orderSumm");
			}
			orderSumm.getCoronaKit().setPersonName(usrName);
			orderSumm.getCoronaKit().setEmail(usrEmail);
			orderSumm.getCoronaKit().setContactNumber(usrMobile);
			try {
				orderSumm.getCoronaKit().setId(kitDAO.getNextCoronaKitId());
			} catch (Exception e) {
				request.setAttribute("exception", e);
				view = "errorPage.jsp";
			}

		} else {
			request.setAttribute("txErrMsg", "Make sure UserNam e/ Email /Mobile are not blank or empty");
			view = "newuser.jsp";
		}

		return view;
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) {
		String view = "showproductstoadd.jsp";
		try {
			List<ProductMaster> products = productMasterDao.getAllProducts();
			request.setAttribute("products", products);
		} catch (Exception exception) {
			request.setAttribute("exception", exception);
			view = "errorPage.jsp";
		}
		return view;
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {
		String view = "user?action=showproducts";
		String productId = request.getParameter("productid");
		if (productId != null) {
			Set<Integer> addedProducts = null;
			HttpSession session = request.getSession();
			if (session.getAttribute("addedProducts") == null)
				session.setAttribute("addedProducts", new HashSet<Integer>());
			addedProducts = (Set<Integer>) session.getAttribute("addedProducts");
			if (request.getParameter("productid") != null)
				addedProducts.add(Integer.parseInt(request.getParameter("productid")));
		} else {
			request.setAttribute("exMsg", "Add Item : Couldn't fetch product id");
			view = "errorPage.jsp";
		}
		return view;
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {

		String view = "user?action=showproducts";
		String productId = request.getParameter("productid");
		if (productId != null) {
			Set<Integer> addedProducts = null;
			HttpSession session = request.getSession();
			if (session.getAttribute("addedProducts") != null) {
				addedProducts = (Set<Integer>) session.getAttribute("addedProducts");
				if (request.getParameter("productid") != null)
					addedProducts.remove(Integer.parseInt(request.getParameter("productid")));
			}
		} else {
			request.setAttribute("exMsg", "Delete Item : Couldn't fetch product id");
			view = "errorPage.jsp";
		}
		return view;
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) {
		String view = "showkit.jsp";
		List<ProductMaster> products;
		Set<Integer> addedProducts = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("addedProducts") != null) {
			addedProducts = (Set<Integer>) session.getAttribute("addedProducts");
			products = new ArrayList<ProductMaster>();
			for (Integer productId : addedProducts) {
				try {
					ProductMaster productMaster = productMasterDao.getProductById(productId);
					products.add(productMaster);
				} catch (Exception exception) {
					request.setAttribute("exception", exception);
					view = "errorPage.jsp";
					break;
				}
			}
			request.setAttribute("products", products);
		} else {
			request.setAttribute("txErrMsg", "No products are selected!!");
		}
		return view;
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		String view = "placeorder.jsp";
		OrderSummary orderSumm = (OrderSummary) request.getSession().getAttribute("orderSumm");
		if (orderSumm != null) {
			double totalAmount = 0;
			Enumeration<String> parameterNames = request.getParameterNames();
			Map<Integer, Integer> prdctQntyMap = new HashMap<Integer, Integer>();
			try {
				while (parameterNames.hasMoreElements()) {
					String paramName = parameterNames.nextElement();
					if (paramName.matches("pid\\d+")) {
						int quantity = Integer.parseInt(request.getParameterValues(paramName)[0]);
						prdctQntyMap.put(Integer.parseInt(paramName.replace("pid", "")), quantity);

					}
				}
				for (Entry<Integer, Integer> entry : prdctQntyMap.entrySet()) {
					ProductMaster product = productMasterDao.getProductById(entry.getKey());
					KitDetail kitDtls = new KitDetail();
					kitDtls.setCoronaKitId(orderSumm.getCoronaKit().getId());
					kitDtls.setProductId(product.getId());
					kitDtls.setQuantity(entry.getValue());
					Double kitAmnt = entry.getValue() * Double.parseDouble(product.getCost());
					kitDtls.setAmount(kitAmnt);
					totalAmount += kitAmnt;
					orderSumm.getKitDetails().add(kitDtls);
					orderSumm.getCoronaKit().setTotalAmount(totalAmount);
				}
			} catch (NumberFormatException exception) {
				request.setAttribute("txErrMsg", "Quantity can only be whole number. Please check");
				view = "user?action=showkit";
			} catch (Exception exception) {
				request.setAttribute("exception", exception);
				view = "errorPage.jsp";
			}

		} else {
			request.setAttribute("exMsg", "Couldn't fetch order status");
			view = "errorPage.jsp";
		}

		return view;
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {
		String view = "placeorder.jsp";
		try {
			if (request.getParameter("address") == null) {
				request.setAttribute("txErrMsg", "Couldn't fetch the address details");
			}
			if (request.getParameter("address").trim().length() == 0) {
				request.setAttribute("txErrMsg", "Address can't be blanck");
			} else {
				
				OrderSummary orderSumm = (OrderSummary) request.getSession().getAttribute("orderSumm");
				orderSumm.getCoronaKit().setDeliveryAddress(request.getParameter("address"));
				orderSumm.getCoronaKit().setOrderDate(LocalDateTime.now().toString());
				orderSumm.getCoronaKit().setOrderFinalized(true);
				List<KitDetail> kitDtls = orderSumm.getKitDetails();
				for (KitDetail kit : kitDtls) {
					int nextKitId = kitDAO.getNextKitId();
					kit.setId(nextKitId);
					kitDAO.addKit(kit);
				}
				view = "user?action=ordersummary";
			}
		} catch (Exception e) {
			request.setAttribute("exception", e);
			view = "errorPage.jsp";
		}

		return view;
	}

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) {
		String view = "ordersummary.jsp";
		OrderSummary orderSumm = (OrderSummary) request.getSession().getAttribute("orderSumm");
		if (orderSumm != null) {
			request.setAttribute("coronaKit", orderSumm.getCoronaKit());
			request.setAttribute("kitDetails", orderSumm.getKitDetails());
			request.getSession().setAttribute("orderSumm", null);
			request.getSession().setAttribute("addedProducts",null);
		} else {
			request.setAttribute("exMsg", "Couldn't fetch the address details");
			view = "errorPage.jsp";
		}
		return view;
	}

}