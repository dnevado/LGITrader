<div id="top-menu">
	
	<ul>
		<#list nav_items as nav_item>
			<#if nav_item.isSelected()>
				<li class="active">
			<#else>
				<li>
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