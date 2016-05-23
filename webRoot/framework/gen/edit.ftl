[#ftl]
<html>
	<head>
	[@script src=['jquery']/]
	<script>
		function addtab(){
			$.ajax({
					url: "/framework/gen/editlist/data.ftl",
					type: "POST",
					async: false,
					success: function(data){
					 	$('#beantb').append(data);
					},
					dataType: "html"
			});
		}
		
		function addprikey(){
			$.ajax({
					url: "/framework/gen/editlist/prikey.ftl",
					type: "POST",
					async: false,
					success: function(data){
					 	$('#beantb').append(data);
					},
					dataType: "html"
			});
		}
		
		function addavailable(){
			$.ajax({
					url: "/framework/gen/editlist/available.ftl",
					type: "POST",
					async: false,
					success: function(data){
					 	$('#beantb').append(data);
					},
					dataType: "html"
			});
		}
		function adddorder(){
			$.ajax({
					url: "/framework/gen/editlist/dorder.ftl",
					type: "POST",
					async: false,
					success: function(data){
					 	$('#beantb').append(data);
					},
					dataType: "html"
			});
		}
		
		function addpass(){
			$.ajax({
					url: "/framework/gen/editlist/pass.ftl",
					type: "POST",
					async: false,
					success: function(data){
					 	$('#beantb').append(data);
					},
					dataType: "html"
			});
		}
		
		
		function addimg(){
			$.ajax({
					url: "/framework/gen/editlist/img.ftl",
					type: "POST",
					async: false,
					success: function(data){
					 	$('#beantb').append(data);
					},
					dataType: "html"
			});
		}
		
		function addbrief(){
			$.ajax({
					url: "/framework/gen/editlist/brief.ftl",
					type: "POST",
					async: false,
					success: function(data){
					 	$('#beantb').append(data);
					},
					dataType: "html"
			});
		}
		
		function addstring(){
			$.ajax({
					url: "/framework/gen/editlist/stringinput.ftl",
					type: "POST",
					async: false,
					success: function(data){
					 	$('#beantb').append(data);
					},
					dataType: "html"
			});
		}
		
		
		function addimgmore(){
			$.ajax({
					url: "/framework/gen/editlist/addimgmore.ftl",
					type: "POST",
					async: false,
					success: function(data){
					 	$('#beantb').append(data);
					},
					dataType: "html"
			});
		}
		
		function addint(){
			$.ajax({
				url: "/framework/gen/editlist/addint.ftl",
				type: "POST",
				async: false,
				success: function(data){
				 	$('#beantb').append(data);
				},
				dataType: "html"
			});
		}
		
		function addpid(){
			$.ajax({
				url: "/framework/gen/editlist/pid.ftl",
				type: "POST",
				async: false,
				success: function(data){
				 	$('#beantb').append(data);
				},
				dataType: "html"
			});
		}
			
			function adddate(){
				$.ajax({
					url: "/framework/gen/editlist/date.ftl",
					type: "POST",
					async: false,
					success: function(data){
					 	$('#beantb').append(data);
					},
					dataType: "html"
			});
		}
				function adddouble(){
					$.ajax({
						url: "/framework/gen/editlist/double.ftl",
						type: "POST",
						async: false,
						success: function(data){
						 	$('#beantb').append(data);
						},
						dataType: "html"
				});
		}
		
		$(function(){
			 $(document).keypress(function(e) {
				 if(e.which ==61){
					 addtab();
				 }
			 });
			 addprikey();
			 
		});
		
		function fromsub(){
			var tbval = $('#tablename').val();
			if(tbval ==null || tbval.length==0){
				alert("请填写表名");
				return false;
				
			}
			
			
			$('#formadd').submit();
		}
		
		
	</script>
	</head>
	<body>
		<input type="button" value="button" onclick="addtab()"/> 
		<input type="button" id="key" value="id主键" onclick="addprikey()"/> 
		<input type="button" value="available" onclick="addavailable()"/> 
		<input type="button" value="dorder" onclick="adddorder()"/> 
		<input type="button" value="pass" onclick="addpass()"/> 
		<input type="button" value="brief" onclick="addbrief()"/> 
		<input type="button" value="img单图" onclick="addimg()"/> 
		<input type="button" value="string" onclick="addstring()"/> 
		<input type="button" value="Integer" onclick="addint()"/> 
		<input type="button" value="addtime" onclick="adddate()"/> 
		<input type="button" value="Double" onclick="adddouble()"/> 
		<br/><br/>
		
		<input type="button" value="img多图" onclick="addimgmore()"/> 
		<input type="button" value="父子pid" onclick="addpid()"/> 
		<br/>
		${msg}
		<br/>
		
		<form action="/genv/fromjavasave.do" method="post" id="formadd">
			<table>
				<tr>
					<td style="width:600px;">
						表名：<input type="text" name="tablename" value="${tablename}" id="tablename"/>
						内容别名：<input type="text" name="tablenamealias" value="tablenamealias" id="tablenamealias"/>
					</td>
				</tr>
			
			</table>
			<table id="beantb">
				
				<tr><td style="width:100px;">类型</td>
				<!-- <td style="width:100px;">主键</td> -->
					<td style="width:100px;">长度</td>
					<td style="width:100px;">名称</td>
					<td style="width:150px;">别名</td>
				<!-- 	<td style="width:300px;">available-pass-dorder</td> -->
					<td style="width:100px;">列表显示</td>
					<td style="width:100px;">条件查询</td>
					<td style="width:100px;">列表形式</td>
					<td style="width:100px;">edit</td>
					<td style="width:100px;">排序</td>
					
				</tr>
				[#if list]
					[#list list as type]

						<tr>
							<td>
								<select name="type">
									<option value="String" [#if type.fieldtype =="String"]selected="selected"[/#if]>String</option>
									<option value="Integer" [#if type.fieldtype =="Integer"]selected="selected"[/#if]>Integer</option>
									<option value="Double" [#if type.fieldtype =="Double"]selected="selected"[/#if]>Double</option>
								</select>
							</td>
							
							<select name="key"  style="display:none;">
								<option value="0" >null</option>
								<option value="1">key</option>
							</select>
							<td>
							<input type="text" name="name" value="${type.fieldname}" style="width:100px;"/>
							</td>
							<td>
							<input type="text" name="alias" value="${type.alias}" style="width:100px;"/>
							</td>
							<td style="display:none;">
								<select name="pro">
									<option value="0">null</option>
									<option value="1">pass</option>
									<option value="2">available</option>
									<option value="3">ascdorder</option>
									<option value="4">descdorder</option>
									
								</select>
							</td>
							<td>
								<select name="list">
									<option value="1">list</option>
									<option value="0">nolist</option>
								</select>
							</td>
							<td>
								<select name="search">
									<option value="0">nosearch</option>
									<option value="1">search</option>
								</select>
							</td>
							<td>
								<select name="showtype">
									<option value="0">null</option>
									<option value="2">img</option>
									<option value="3">dorder</option>
									<option value="4">pass</option>
								</select>
							</td>
							<td>
								<select name="edittype">
									<option value="0">input</option>
									<option value="1">null</option>
									<option value="2">textarea</option>
									<option value="3">photo</option>
									
								</select>
							</td>
							<td>
								<input type="text" name="beandorder" value="" style="width:50px;"/>
							</td>
						</tr>
					[/#list]
				[/#if]
			
			</table>
			
		</form>	
		
		<input type="button" value="提交" onclick="fromsub();"/>
	
		[#if sql ??]
			<form  action="/genv/addsql.do" method="post">
					<input type="hidden" name="table" value="${tablename}"/>
					<table>
						<tr>
							<td>
								<textarea name="sql" style="width:800px;height:200px;">${sql}</textarea>
							</td>
						</tr>
						<tr>
							<td>
								<input type="submit" value="提交"/>
							</td>
						</tr>
					</table>
			</form>
		[/#if]
	</body>
</html>