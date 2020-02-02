<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
	
	<#assign 
	logo_profi = theme_display.getThemeSetting('default-profi-business-logo')
	footer_company_name = theme_display.getThemeSetting('footer-company-name')
	footer_social_bar = theme_display.getThemeSetting('footer-social-bar')
	footer_phone_show = theme_display.getThemeSetting('footer-phone-show')
	footer_phone = theme_display.getThemeSetting('footer-phone')
	footer_e_mail_show = theme_display.getThemeSetting('footer-e-mail-show')
	footer_e_mail = theme_display.getThemeSetting('footer-e-mail')
	footer_fb = theme_display.getThemeSetting('footer-fb')
	footer_fb_url = theme_display.getThemeSetting('footer-fb-url')
	footer_google_plus = theme_display.getThemeSetting('footer-google-plus')
	footer_google_plus_url = theme_display.getThemeSetting('footer-google-plus-url')
	footer_twitter = theme_display.getThemeSetting('footer-twitter')
	footer_twitter_url = theme_display.getThemeSetting('footer-twitter-url')
	footer_linkedin = theme_display.getThemeSetting('footer-linkedin')
	footer_linkedin_url = theme_display.getThemeSetting('footer-linkedin-url')
	footer_address_line1 = theme_display.getThemeSetting('footer-address-line1')
	footer_address_line2 = theme_display.getThemeSetting('footer-address-line2')
	footer_address_line3 = theme_display.getThemeSetting('footer-address-line3')
	footer_address_phone = theme_display.getThemeSetting('footer-address-phone')
	footer_address_fax = theme_display.getThemeSetting('footer-address-fax')
	footer_company_logo = theme_display.getThemeSetting('footer-company-logo')
	>
	
	<script>
    define._amd = define.amd;
    define.amd = false;
	</script>
	<script src="${javascript_folder}/bootstrap-datepicker.min.js"></script>		
	<script>
	    define.amd = define._amd;
	</script>

	<link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
	<link href="${css_folder}/datepicker/bootstrap-datepicker.css" rel="stylesheet">		



</head>


<#assign permission_checker = themeDisplay.getPermissionChecker() />

<#assign is_group_admin = permission_checker.isGroupAdmin(group_id) />
<#assign is_omniadmin = permission_checker.isOmniadmin() />

<#assign admin = is_omniadmin/>

<!-- PARA QUITAR LOS CONTROLES DE LF7 PARA LOS USERS, BUG -->

<#if admin>
	<body class="${css_class} omniadmin_yes">
<#else>
	<body class="${css_class} omniadmin_no">
</#if>

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />



<div id="wrapper" class="container-fluid">
		<header>
			<#if has_navigation || is_signed_in>
							<#include "${full_templates_path}/menu.ftl" />
		   </#if>
		</header>
		<div id="content">
			<div id="main-content">				
				<#if selectable>
					<@liferay_util["include"] page=content_include />
				<#else>
					${portletDisplay.recycle()}

					${portletDisplay.setTitle(the_title)}

					<@liferay_theme["wrap-portlet"] page="portlet.ftl">
						<@liferay_util["include"] page=content_include />
					</@>
				</#if>		
			</div>		
		</div>
	</div>
	<div id="footer">
		<div id="footer-content">
			<div class="foter-content-row">
					<h3>${footer_company_name}</h3>
			</div>
			<#if footer_social_bar == 'true' >
				<div class="foter-content-row-line">
					<#if footer_phone_show == 'true'>
						<a class="phoneNumber" rel="nofollow" href="#"> ${footer_phone} </a>
					</#if>
					
					<#if footer_e_mail_show == 'true'>
						<a class="email" rel="nofollow" href="mailto:${footer_e_mail}"> ${footer_e_mail} </a>
					</#if> 
					
					<#if footer_fb == 'true'>
						<a class="footer-icon" href="${footer_fb_url}" target="_blank"><i class="icon-facebook"></i></a>
					</#if>
					
					<#if footer_google_plus == 'true'>
						<a class="footer-icon" href="${footer_google_plus_url}" target="_blank"><i class="icon-google-plus"></i></a>
					</#if>
					
					<#if footer_twitter == 'true'>
						<a class="footer-icon" href="${footer_twitter_url}" target="_blank"><i class="icon-twitter"></i></a> 
					</#if>
					
					<#if footer_linkedin == 'true'>
						<a class="footer-icon" href="${footer_linkedin_url}" target="_blank"><i class="icon-linkedin"></i></a>
					</#if>
					<div class="clear"></div>
				</div>
			</#if>
			<div class="foter-content-row">
				<div class="col-left">
					${footer_address_line1}<br/>
					${footer_address_line2} <br/>
					${footer_address_line3}<br/>
					<#if footer_address_phone != ''>
						tel: ${footer_address_phone}<br/>
					</#if>
					
					<#if footer_address_fax != ''>
						fax: ${footer_address_fax}
					</#if>
					
				</div>
				<div class="col-right">
					<#if footer_company_logo == 'true'>
						<#if logo_profi == 'true'>
							<img class="topLogo" alt="IBTrader-theme Logo" src="${images_folder}/profi/site_logo_footer.png"/>
						<#else>
							<img class="topLogo" alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
						</#if>
					</#if>				
					<br/>					
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>	

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>