	<nav class="navbar navbar-inverse navbar-global navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">
						<#if logo_profi == 'true' >
							<img class="topLogo" alt="IBTrader-theme Logo" src="${images_folder}/profi/site_logo.png"/>
						<#else>
							<img class="topLogo" alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
						</#if>
		  </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-user navbar-right">
            <li><a href="/web${theme_display.getScopeGroup().getFriendlyURL()}/perfil"><span class="glyphicon glyphicon-user"></span>  ${user.getLastName()} ${user.getFirstName()}</a></li>
            <li><a href="/c/portal/logout"><span class="glyphicon glyphicon-log-out"></span></a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
	<nav class="navbar-primary">
	  <a href="#" class="btn-expand-collapse"><span class="glyphicon glyphicon-menu-left"></span></a>
	  <ul class="navbar-primary-menu">
		
			<#list nav_items as nav_item>
				<#if nav_item.isSelected()>
					<li  class="nav-item  active">
				<#else>
					<li class="nav-item">
				</#if>
					<a href="${nav_item.getURL()}" ${nav_item.getTarget()}>
					<#if nav_item.getLayout().getIconImage()>
						<span class="icon">  <img src="/image/layout_icon?img_id=${nav_item.getLayout().getIconImageId()}"/></span>	
					</#if>					
					<span class="nav-label">${nav_item.getName()}</span>
					</a>
				  	<#if nav_item.hasChildren()>
					<div class="child-menu">
						<ul>
							<#list nav_item.getChildren() as nav_child>
								<#if nav_child.isSelected()>
									<li class="active">
								<#else>
									<li>
								</#if>
									<a href="${nav_child.getURL()}" ${nav_child.getTarget()}>><span class="nav-label">${nav_child.getName()}</span></a>
															
								</li>
							</#list>
						</ul>
					</div>
					</#if>
				</li>
			</#list>	
	  </ul>
	</nav>
<script>
$('.navbar-primary').toggleClass('collapsed');
$('.btn-expand-collapse').click(function(e) {
				e.preventDefault();
				$('.navbar-primary').toggleClass('collapsed');
});
</script>