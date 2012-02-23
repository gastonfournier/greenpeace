
<div class="row-fluid">
	<div class="span12">
		<h2>
			${config.project.name}
			in
			${config.environment1.name}
		</h2>
	</div>
	<!--/span-->
</div>
<div class="row-fluid">
	<div class="span12">
	
		<g:set var="defaultTag" value="${config.project.defaultTag}"/>

		<ul class="nav nav-tabs">
			<g:each var="tag" in="${config.overrides*.tag}">
				<li${tag == defaultTag ? ' class="active"' : ''}><a href="#${tag.encodedName}" data-toggle="tab">
						${tag.name}
				</a></li>
			</g:each>
		</ul>

		<div class="tab-content">
			<g:each var="ov" in="${config.overrides}">
				<div class="tab-pane fade${ov.tag == defaultTag ? ' active in' : ''}" id="${ov.tag.encodedName}">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th style="width: 40%">Key</th>
								<th style="width: 60%">Value</th>
							</tr>
						</thead>
						<tbody>
							<g:each var="prop" in="${ov.effectiveProperties()}">
								<tr>
									<td title="${prop.key}">
										${prop.key}
									</td>
									<td${prop.usingDefault ? ' class="muted"' : ''}>
										<div id="${prop.key}" class="propVal" title="${prop.value}">${prop.value}</div>
									</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
				
				<script type="text/javascript">
					var url = "${createLink(controller: 'browse', action: 'updatePropValue', params: [id: ov.id])}";
					var tag = "${ov.tag.encodedName}";
					makeEditable(url, tag);
				</script>
				
			</g:each>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

