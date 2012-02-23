
<ul class="nav nav-list">
	<li class="nav-header">
		<div class="accordion" id="sideAccordion">
			<g:each var="parent" in="${parents}" status="i">
				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="accordion-toggle" data-toggle="collapse"
							data-parent="#sideAccordion" href="#collapse${i}">
							${parent.key.name}
						</a>
					</div>
					<div id="collapse${i}" class="accordion-body collapse">
						<div class="accordion-inner">
							<ul class="nav nav-list">
								<g:each var="child" in="${parent.value}">
									<li>
										<a href="javascript:loadConfig_${actionName}('${parent.key.name}', '${child.name}')">${child.name}</a>
									</li>
								</g:each>
							</ul>
						</div>
					</div>
				</div>
			</g:each>
		</div>
	</li>
</ul>
