<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

User selUser = themeDisplay.getUser();		 
		 
Contact selContact = null;

if (selUser != null) {
	selContact = selUser.getContact();
}

PasswordPolicy passwordPolicy = null;

if (selUser == null) {
	passwordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(company.getCompanyId());
}
else {
	passwordPolicy = selUser.getPasswordPolicy();
}

%>

<portlet:actionURL name="/users_admin/edit_user" var="editUserActionURL" />

<portlet:renderURL var="editUserRenderURL">
	<portlet:param name="mvcRenderCommandName" value="/users_admin/edit_user" />
	<portlet:param name="backURL" value="<%= backURL %>" />
</portlet:renderURL>

<aui:form action="<%= editUserActionURL %>" cssClass="container-fluid-1280 portlet-users-admin-edit-user" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (selUser == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= editUserRenderURL %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="p_u_i_d" type="hidden" value="<%= (selUser != null) ? selUser.getUserId() : 0 %>" />

	<%
	request.setAttribute("user.passwordPolicy", passwordPolicy);
	request.setAttribute("user.selContact", selContact);
	request.setAttribute("user.selUser", selUser);

	request.setAttribute("addresses.className", Contact.class.getName());
	request.setAttribute("emailAddresses.className", Contact.class.getName());
	request.setAttribute("phones.className", Contact.class.getName());
	request.setAttribute("websites.className", Contact.class.getName());

	if (selContact != null) {
		request.setAttribute("addresses.classPK", selContact.getContactId());
		request.setAttribute("emailAddresses.classPK", selContact.getContactId());
		request.setAttribute("phones.classPK", selContact.getContactId());
		request.setAttribute("websites.classPK", selContact.getContactId());
	}else {
		request.setAttribute("addresses.classPK", 0L);
		request.setAttribute("emailAddresses.classPK", 0L);
		request.setAttribute("phones.classPK", 0L);
		request.setAttribute("websites.classPK", 0L);
	}
	%>
	<c:choose>
		<c:when  test="<%=campusElEspanolBehaviour %>">
			<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" id="details" label="custom.my.account.details">
				<jsp:include page='/user/details.jsp'/>
			</aui:fieldset>
			<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" id="address" label="custom.my.account.address">
				<jsp:include page='/user/addresses.jsp'/>
			</aui:fieldset>
			<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" id="social" label="custom.my.account.social">
				<jsp:include page='/user/social_network.jsp'/>
			</aui:fieldset>			
			<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" id="password" label="custom.my.account.password">	
				<jsp:include page='/user/password.jsp'/>
			</aui:fieldset>
			<aui:button-row>
				<aui:button cssClass="btn-lg" primary="<%= true %>" type="submit" />
			</aui:button-row>
		</c:when>
		<c:otherwise>
			<liferay-ui:form-navigator
				backURL="<%= backURL %>"
				formModelBean="<%= selUser %>"
				id="<%=MyAccountFormNavigatorConstants.CUSTOM_MY_ACCOUNT %>"
				markupView="lexicon"
			/>
		</c:otherwise>
	</c:choose>
</aui:form>

<aui:script>
	function <portlet:namespace />createURL(href, value, onclick) {
		return '<a href="' + href + '"' + (onclick ? ' onclick="' + onclick + '" ' : '') + '>' + value + '</a>';
	}

	function <portlet:namespace />saveUser(cmd) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = cmd;

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>