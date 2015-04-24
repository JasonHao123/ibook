<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div data-dojo-type="dijit/layout/BorderContainer"
	data-dojo-props="region:'center',gutters:true, liveSplitters:false"
	id="borderContainerTwo22">

	<div data-dojo-type="dijit/layout/ContentPane"
		data-dojo-props="region:'top', splitter:false">
		<div id="toolbar1" data-dojo-type="dijit/Toolbar">

	<div data-dojo-type="dijit/form/DropDownButton" id="settingsBtn" style=""
		data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings', showLabel:true">	
		<span>Move</span>
		<div data-dojo-type="dijit/DropDownMenu">
	        <div data-dojo-type="dijit/MenuItem" id="phoenixAdmBtn" style="width: 120px;"
	        	data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings'">	 
	        	<script type="dojo/on" data-dojo-event="click" data-dojo-args="event">
					showInDialog('Move Folder','<c:url value="/spring/tree?root=PUBLIC_FOLDER"/>')
				</script>	
	        	Public
	        </div>
	        <div data-dojo-type="dijit/MenuItem" id="groupAdmBtn" style="width: 120px;"
	        	data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings'" >	 
	        	<script type="dojo/on" data-dojo-event="click" data-dojo-args="event">
				</script>	
	        	Group
			</div>
	        <div data-dojo-type="dijit/MenuItem" id="rootFolderAdmBtn" style="width: 120px;"
	        	data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings'" >	 
	        	<script type="dojo/on" data-dojo-event="click" data-dojo-args="event">
 				</script>	
	        	Personal
			</div>
		</div>
	</div>
	<div data-dojo-type="dijit/form/DropDownButton" id="copyBtn" style=""
		data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings', showLabel:true">	
		<span>Copy</span>
		<div data-dojo-type="dijit/DropDownMenu">
	        <div data-dojo-type="dijit/MenuItem" id="copy.public" style="width: 120px;"
	        	data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings'">	 
	        	<script type="dojo/on" data-dojo-event="click" data-dojo-args="event">
				</script>	
	        	Public
	        </div>
	        <div data-dojo-type="dijit/MenuItem" id="copy.group" style="width: 120px;"
	        	data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings'" >	 
	        	<script type="dojo/on" data-dojo-event="click" data-dojo-args="event">
				</script>	
	        	Group
			</div>
	        <div data-dojo-type="dijit/MenuItem" id="copy.personal" style="width: 120px;"
	        	data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings'" >	 
	        	<script type="dojo/on" data-dojo-event="click" data-dojo-args="event">
 				</script>	
	        	Personal
			</div>
		</div>
	</div>

	<div data-dojo-type="dijit/form/DropDownButton" id="linkBtn" style=""
		data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings', showLabel:true">	
		<span>Link</span>
		<div data-dojo-type="dijit/DropDownMenu">
	        <div data-dojo-type="dijit/MenuItem" id="link.public" style="width: 120px;"
	        	data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings'">	 
	        	<script type="dojo/on" data-dojo-event="click" data-dojo-args="event">
				</script>	
	        	Public
	        </div>
	        <div data-dojo-type="dijit/MenuItem" id="link.group" style="width: 120px;"
	        	data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings'" >	 
	        	<script type="dojo/on" data-dojo-event="click" data-dojo-args="event">
				</script>	
	        	Group
			</div>
	        <div data-dojo-type="dijit/MenuItem" id="link.personal" style="width: 120px;"
	        	data-dojo-props="iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIconSettings'" >	 
	        	<script type="dojo/on" data-dojo-event="click" data-dojo-args="event">
 				</script>	
	        	Personal
			</div>
		</div>
	</div>

			<div data-dojo-type="dijit/form/Button" id="toolbar1.cut"
				data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconDelete', showLabel:true">Delete</div>

			<span data-dojo-type="dijit/ToolbarSeparator"></span>
			<div data-dojo-type="dijit/form/Button" data-dojo-id="toolbar1.copy"
				data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconCopy', showLabel:true,onClick:showNewFolderDialog">New
				Folder</div>

			<div data-dojo-type="dijit/form/Button"
				data-dojo-id="toolbar1.upload"
				data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconCopy', showLabel:true,onClick:showUploadDialog">Upload</div>
			<div data-dojo-type="dijit/form/Button" id="toolbar1.paste"
				data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconPaste', showLabel:true">Download</div>


			<div data-dojo-type="dijit/form/ComboButton" style="float: right;"
				data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconBold', showLabel:false">
				<span>Mode</span>
				<div data-dojo-type="dijit/DropDownMenu">
					<div data-dojo-type="dijit/MenuItem"
						data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconBold',onClick:function(){page1.set('href','details')}">Details</div>
					<div data-dojo-type="dijit/MenuItem"
						data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconBold',onClick:function(){page1.set('href','details')}">Thumbnail</div>
				</div>
			</div>
			
		</div>
		<div data-dojo-type="dijit/layout/ContentPane" title="Questions"
			style="height: 500px; overflow: auto;"
			data-dojo-props="region:'center'" data-dojo-id="page1" id="page1">
			<span data-dojo-type="dojox/data/JsonRestStore"
				data-dojo-id="myStore"
				data-dojo-props="target:'<c:url value="/spring/fs/folder"/>',idAttribute:'id'">
			</span>
					<div id="grid" data-dojo-type="dojox/grid/EnhancedGrid" style="width: 100%;height: 750px" class="mainTable"
						    data-dojo-props="keepSelection:false,
                            structure:[
                            {name: 'Type', field: 'type','width': '5%',
                                type: dojox.grid.cells._Widget,
                                formatter: function(type)
                                {
                                    return new dijit.form.Button
                                    (
                                        {
                                        iconClass:'dijitPhoenixToolbarIcon dijitPhoenixToolbarIcon'+type,
                                        showLabel:false,
                                        label:(type=='FILE'?'File':type=='FOLDER'?'Folder':'Link')
                                        }
                                    )
                                }
                            },
                            {name: 'Title', field: 'name','width': '45%'},

                            {name: 'Author', field: 'creatorString','width': '12%'},
                            {name: 'Date', field: 'created','width': '7%'}],
                            store:myStore,
                            plugins:{ pagination: {position:'top'}}" >
						
						
						 <script type="dojo/on" data-dojo-event="rowDblClick" data-dojo-args="e">
                                 var grid = dijit.byId("grid");
                                 var item = grid.getItem(e.rowIndex);
								 console.log("double click on "+item);
						         if ("FOLDER" == item.type || "FOLDER_LINK" == item.type) {									
							        dojo.publish("displayFolder", [item]);
									dojo.publish("selectNode", [item.idPath]);
									dojo.publish("displayFolderFilter", [item]);
						         }else if("FILE"==item.type || "FILE_LINK"==item.type){
									// display document infor
									 dojo.publish("displayFolder", [item]);
								 }else {
									// when type is notes, open the conversation
								 }
							
							</script>
							 <script type="dojo/on" data-dojo-event="selectionChanged" data-dojo-args="e">
								 var grid = dijit.byId("grid");
								 var items = grid.selection.getSelected();
								 if(items.length ==1 && items[0]!=null) {
 									console.log("select  on "+items[0]);
									if('FILE' == items[0].type || 'LINK_FILE' == items[0].type ) {							 
						            	dojo.publish("displayFileInfo", [items[0].id]);	
									}else if("FOLDER" == items[0].type || "LINK_FOLDER" == items[0].type) {
										dojo.publish("displayFolderInfo", [items[0].id]);	
									}
								 }					
							</script>
						   
					</div>			
		</div>
		<!-- end TabContainer -->
	</div>
	<!-- end BorderContainer -->
</div>