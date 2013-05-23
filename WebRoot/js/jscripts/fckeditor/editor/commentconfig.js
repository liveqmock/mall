FCKConfig.ToolbarSets["Default"] = [
	['Source','DocProps'],
	['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'],
	['OrderedList','UnorderedList','-','Outdent','Indent','Blockquote','CreateDiv'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],
	'/',
	['Link','Unlink','Anchor'],
	['Image','Flash','Table','Rule','Smiley','SpecialChar','PageBreak'],
	'/',
	['Style','FontFormat','FontName','FontSize'],
	['TextColor','BGColor'],
	['FitWindow','ShowBlocks','-','About']		// No comma for the last row.
] ;
/*
FCKConfig.ToolbarSets["Comment"] = [
	['TextColor','BGColor','Bold','Italic','OrderedList','UnorderedList']
] ;*/
FCKConfig.ToolbarSets["Comment"] = [] ;
//FCKConfig.ToolbarStartExpanded = false ;//启动fckeditor工具栏默认是否展开       
//FCKConfig.ToolbarCanCollapse = true ;//是否允许折叠或展开工具栏       
FCKConfig.EnterMode = 'br' ;			// p | div | br
FCKConfig.ShiftEnterMode = 'p' ;	// p | div | br
FCKConfig.ContextMenu=[];
FCKConfig.BrowserContextMenu = false ;
FCKConfig.BrowserContextMenuOnCtrl = false ; //是否允许在编辑区域中当按下Ctrl键时，点击鼠标右键显示浏览器的上下文菜单 
FCKConfig.BrowserContextMenu = false ; //是否允许在编辑区域中点击鼠标右键显示浏览器的上下文菜单 
FCKConfig.ForcePasteAsPlainText   = false;//强制粘贴为纯文本   
//FCKConfig.Keystrokes = [];
FCKConfig.SkinPath = FCKConfig.BasePath + 'skins/comment/' ;
FCKConfig.SkinEditorCSS = '' ;	// FCKConfig.SkinPath + "|<minified css>" ;
FCKConfig.SkinDialogCSS = '' ;	// FCKConfig.SkinPath + "|<minified css>" ;