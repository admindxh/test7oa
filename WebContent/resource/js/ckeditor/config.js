/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	 //config.language = 'zh-cn';
	// config.uiColor = '#57BCF0';
	 //config.skin = 'office2003';
	/* config.toolbarGroups = [
	                         { name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
	                 { name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
	                         { name: 'links' },
	                 { name: 'insert' },
	                 { name: 'forms' },
	                 { name: 'tools' },
	                 { name: 'document',    groups: [ 'mode', 'document', 'doctools' ] },
	                 { name: 'others' },
	                 '/',
	                 { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
	                 { name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align' ] },
	                 { name: 'styles' },
	                 { name: 'colors' },
	                 { name: 'about' }
	                 ];*/
	// config.toolbar="Base",
	/* config.toolbar_Base = [ 
               { name: 'document', items: [ 'Source', '-', 'NewPage', 'Preview', '-', 'Templates' ] }, 
               { name: 'clipboard', items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 
           'Undo', 'Redo' ] }, 
               '/', 
               { name: 'basicstyles', items: [ 'Bold', 'Italic' ] } 
           ]; */
	 config.toolbarGroups = [
	                    {name: "document",groups: ["mode", "document", "doctools"]},
						{name: "clipboard",groups: ["clipboard", "undo"]},
						{name: "editing",groups: ["find", "selection", "spellchecker"]},
						//{name: "forms"},
						
						{name: "insert"},
						{name: "basicstyles",groups: ["basicstyles", "cleanup"]},
						{name: "paragraph",groups: ["list", "indent", "blocks", "align"]},
						{name: "links"},
						
						
						{name: "styles"},
						{name: "colors"},
						
						{name: "others"},
						{name: "tools"},
						{name: "about"} 
	 
			],
	config.removeButtons = 'Save';//移除工具按钮
};
