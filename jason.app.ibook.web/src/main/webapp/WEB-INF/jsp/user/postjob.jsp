<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" %>
    <script>
		$( document ).on( "pagecreate", "#myPage", function() {
	        $('#content3').redactor({
	    		imageGetJson: '<c:url value="/spring/files/listImages" />',
	    		imageUpload: '<c:url value="/spring/files/uploadImage" />',
	    		clipboardUploadUrl: '/webUpload/redactor/clipboardUpload/',
	    		fileUpload: '<c:url value="/spring/files/fileUpload" />'
	    	});
	        
            $('#locations').tagit({
        			autocomplete : {
        				source : function(request, response) {
        					$.ajax({
        						url: "http://gd.geobytes.com/AutoCompleteCity",
        						dataType: "jsonp",
        						crossDomain: true,
        						data: {
        							q: request.term
        						},
        						success : function(data) {
        							response($.map(data, function(item) {
        								return {
        									label : item,
        									value : item
        								}
        							}));
        						}
        					});
        				},
        				delay : 1000,
        				minLength : 2
        			},
        			allowSpaces : true,
        			fieldName: "location"
        		});
            

		});
    </script>
	<style>
		.ui-filter-inset {
			margin-top: 0;
		}
    </style>
	<div role="main" class="ui-content  jqm-content">
  <spring:hasBindErrors name="User">
 <p>   <b><spring:message code="page.label.fixerror" text="Please fix all errors!" /></b></p>
  </spring:hasBindErrors>
  	<form data-ajax="false" method="post">	
	<h3>Basic Info</h3>
		<label for="title">Company:<span style="float:right"><a href="#" >Add</a></span></label>
		<select>
			<option></option>
			<option>IBM</option>
		</select>
		<label for="title">Department/Division:</label>
		<select>
			<option></option>
			<option>GBS</option>
			<option>GDC</option>
			<option>CSDL</option>
		</select>
		<label for="title">Category 1:</label>
		<select>
			<option></option>
			<option>IT|互联网|通信</option>
			<option>GDC</option>
			<option>CSDL</option>
		</select>
		<label for="title">Category 2:</label>
		<select>
			<option></option>
			<option>软件/互联网开发/系统集成</option>
			<option>硬件开发</option>
			<option>CSDL</option>
		</select>
				<label for="title">工作性质</label>
		<select>
			<option></option>
			<option>全职</option>
			<option>兼职</option>
			<option>实习</option>
		</select>
		<label for="title">Title:</label>
		<input type="text" name="title">


		<label for="title">招聘人数:</label>
<input type="number" data-clear-btn="true" name="number-4" pattern="[0-9]*" id="number-4" value="">
	
		<label for="locations">Location:</label>
<ul id="locations" class="ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset"></ul>
<label>Job description</label>
<textarea id="content3" rows="5" cols="20"></textarea>
<label>Features<span style="float:right"><a href="#" >Popular features</a></span></label>
<input type="text">
<h3>Requirement</h3>
<label>学历</label>
<select>
	<option></option>
	<option>高中</option>
	<option>大专</option>
	<option>本科</option>
	<option>硕士</option>
	<option>博士</option>
</select>
		 <div data-role="rangeslider">
        <label for="range-1a">Working experience</label>
        <input type="range" name="range-1a" id="range-1a" min="0" max="35" value="0">
        <label for="range-1b">Rangeslider:</label>
        <input type="range" name="range-1b" id="range-1b" min="0" max="35" value="35">
    </div>
<label>Required Skills</label>
<input>
<label>Desired Skills</label>
<input>
<h3>Benefit Info</h3>
	<div data-role="rangeslider">
        <label for="range-1a">Salary(K RMB):</label>
        <input type="range" name="range-1a" id="range-1a" min="0" max="100" value="0">
        <label for="range-1b">Rangeslider:</label>
        <input type="range" name="range-1b" id="range-1b" min="0" max="100" value="100">
    </div>
	<label>基本工资</label>
	<input type="number" data-clear-btn="true" pattern="[0-9]*"  value="12">
	<div class="ui-field-contain">
	 <label for="range-1a">奖金</label>
<input type="checkbox">
</div>
 <label for="range-1a">年假天数</label>
<input type="number" data-clear-btn="true" pattern="[0-9]*"  value="5">
 <label for="range-1a">基本保险</label>
<select>
	<option></option>
	<option>五险一金</option>
</select>
<div class="ui-field-contain">
 <label for="range-1a">商业保险</label>
<input type="checkbox">
</div>
<div class="ui-field-contain">
 <label for="range-1a">企业年金</label>
<input type="checkbox">
</div>
<div class="ui-grid-a">
<div class="ui-block-a"><input type="submit" value="Submit" data-theme="a"></div>
<div class="ui-block-b"><input type="reset" value="Reset" data-theme="b"></div>
</div>
</form>
	</div>