<div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
  </button>
<div id="top-menu" class="collapse navbar-collapse">	
	 <ul class="navbar-nav">
		<#list nav_items as nav_item>
			<#if nav_item.isSelected()>
				<li  class="nav-item  active">
			<#else>
				<li class="nav-item">
			</#if>
				<a href="${nav_item.getURL()}" ${nav_item.getTarget()}>${nav_item.getName()}</a>

				
				<#if nav_item.hasChildren()>
				<div class="child-menu">
					<ul>
						<#list nav_item.getChildren() as nav_child>
							<#if nav_child.isSelected()>
								<li class="active">
							<#else>
								<li>
							</#if>
								<a href="${nav_child.getURL()}" ${nav_child.getTarget()}>${nav_child.getName()}</a>
														
							</li>
						</#list>
					</ul>
				</div>
				</#if>
			</li>
		</#list>
	</ul>
</div>
</div>