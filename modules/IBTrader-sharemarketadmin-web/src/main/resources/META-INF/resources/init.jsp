<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@  page import="com.liferay.portal.kernel.util.*" %>
<%@ page import="com.liferay.taglib.search.*" %>
<%@ page import="com.ibtrader.data.model.Position" %>
<%@ page import="com.ibtrader.data.model.Share" %>
<%@ page import="com.ibtrader.data.model.Market" %>
<%@ page import="com.ibtrader.data.model.Strategy" %>
<%@ page import="com.ibtrader.data.service.ShareLocalServiceUtil" %>
<%@ page import="com.ibtrader.util.ConfigKeys" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.security.permission.ActionKeys" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.theme.PortletDisplay"%>
<%@ page import="com.ibtrader.util.Utilities" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ page import="com.ibtrader.data.model.impl.StrategyImpl"%>
<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<liferay-theme:defineObjects />
<portlet:defineObjects />
