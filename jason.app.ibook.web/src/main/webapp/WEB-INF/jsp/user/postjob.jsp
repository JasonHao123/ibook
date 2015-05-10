<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" %>
    <script>
		$( document ).on( "pagecreate", "#myPage", function() {
			$( "#autocomplete" ).on( "filterablebeforefilter", function ( e, data ) {
				var $ul = $( this ),
					$input = $( data.input ),
					value = $input.val(),
					html = "";
				$ul.html( "" );
				if ( value && value.length > 2 ) {
					$ul.html( "<li><div class='ui-loader'><span class='ui-icon ui-icon-loading'></span></div></li>" );
					$ul.listview( "refresh" );
					$.ajax({
						url: "http://gd.geobytes.com/AutoCompleteCity",
						dataType: "jsonp",
						crossDomain: true,
						data: {
							q: $input.val()
						}
					})
					.then( function ( response ) {
						$.each( response, function ( i, val ) {
							html += "<li>" + val + "</li>";
						});
						$ul.html( html );
						$ul.listview( "refresh" );
						$ul.trigger( "updatelayout");
						$("#popupLogin").popup("open");
						 $($ul).on('click', 'li', function(event) {
							 $("#autocomplete-input").val($(this).text());
							 $ul.empty();
							 $ul.listview( "refresh" );
						 });

					});
				}
			});
		});
    </script>
	<style>
		.ui-filter-inset {
			margin-top: 0;
		}
    </style>
	<div role="main" class="ui-content  jqm-content">
	<form action="">	</form>
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
		<label for="title">Title:</label>
		<input type="text">
		
		<label for="title">Location:</label>
				<form class="ui-filterable">
					<input id="autocomplete-input" data-type="search" placeholder="Find a city...">
				</form>
				<div data-role="popup" id="popupLogin" data-theme="a" class="ui-corner-all" data-position-to="#autocomplete-input">
					<ul id="autocomplete" data-role="listview" data-inset="true" data-filter="true" data-input="#autocomplete-input"></ul>
				</div>

<label>Job description</label>
<textarea rows="5" cols="20"></textarea>
<label>Features</label>
<input type="text">
<h3>Requirement</h3>
		 <div data-role="rangeslider">
        <label for="range-1a">Working experience</label>
        <input type="range" name="range-1a" id="range-1a" min="0" max="35" value="0">
        <label for="range-1b">Rangeslider:</label>
        <input type="range" name="range-1b" id="range-1b" min="0" max="35" value="35">
    </div>
<label>Skills</label>
<input>
<h3>Benefit Info</h3>
		 <div data-role="rangeslider">
        <label for="range-1a">Salary:</label>
        <input type="range" name="range-1a" id="range-1a" min="0" max="100" value="40">
        <label for="range-1b">Rangeslider:</label>
        <input type="range" name="range-1b" id="range-1b" min="0" max="100" value="80">
    </div>
	</div>