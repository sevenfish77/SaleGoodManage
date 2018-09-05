(function ($, undefined) {
    $.fn.tree.extensions = {};
    var _onExpand = $.fn.tree.defaults.onExpand;
    var onExpand = $.fn.tree.extensions.onExpand = function (node) {
        if ($.isFunction(_onExpand)) { _onExpand.apply(this, arguments); }
        var t = $(this), opts = t.tree("options");
        if (opts.onlyNodeExpand) {
            var nodes = t.tree("getNears", node.target), animate = opts.animate
            opts.animate = false;
            $.each($.array.filter(nodes, function (val) { return val.target != node.target && val.state == "open"; }), function () {
                t.tree("collapse", this.target);
            });
            opts.animate = animate;
        }
    };


       var methods = $.fn.tree.extensions.methods = {
       	//  扩展 easyui-tree 的自定义方法；获取指定节点的同级所有节点(包含自身)；该方法定义如下参数：
        //      target:  指定的表示 tree-node 的 jQuery 或 DOM 对象。
        //  返回值：返回 tree-node target 的同级别(具有和当前 target 同一个父级节点)所有节点构成的一个数组对象；
        //      数组中每一个元素都是一个包含属性 id、text、iconCls、checked、state、attributes、target 的 tree-node 对象。
        //      如果传入的参数 target 是根节点或者未定义 target 参数，则该方法和 getRoots 方法返回的值相同；
        //      如果传入的参数 target 不是一个 div.tree-node 或者其不包含在当前 easyui-tree 中，则返回 null。
        getNears: function (jq, target) { return getNears(jq[0], target); }
       };



	  function getNears(treeTarget, target) {
        var t = $(treeTarget); target = $(target);
        if (!$.contains(t[0], target[0]) || !target.is("div.tree-node")) { return null; }
        return target.closest("ul").find("li>div.tree-node").map(function () {
            return t.tree("getNode", this);
        });
    };
    
    function initAutoToggle(t, opts, exts) {
        exts.onClickBak = opts.onClick;
        opts.onClick = function (node) {
        	if(opts.leafClickOpen){base.treeNodeClick(t,node);}
            if ($.isFunction(exts.onClickBak)) { exts.onClickBak.apply(this, arguments); }
            if (opts.toggleOnClick) { t.tree("toggle", node.target); }
        };
    };    
    
    
    function initContextMenu(t, opts, exts) {
        exts.onContextMenuBak = opts.onContextMenu;
        opts.onContextMenu = function (e, node) {
            if ($.isFunction(exts.onContextMenuBak)) { exts.onContextMenuBak.apply(this, arguments); }
            if (opts.selectOnContextMenu) { t.tree("select", node.target); }
            if (opts.enableContextMenu) {
                e.preventDefault();
                var items = parseContextMenuItems(t, opts, e, node);
                if (opts.autoBindDblClick && opts.dblClickMenuIndex >= 0 && $.util.likeArray(opts.contextMenu) && !$.util.isString(opts.contextMenu)
                    && opts.contextMenu.length > opts.dblClickMenuIndex) {
                    items[opts.dblClickMenuIndex].bold = true;
                }
                $.easyui.showMenu({ items: items, left: e.pageX, top: e.pageY });
            }
        };
    };

    var parseQueryParams = $.fn.tree.extensions.parseQueryParams = function (opts, param) {
        var ret = $.extend({}, param, opts.queryParams);
        return $.util.parseMapFunction(ret);
    };



    var initExtensions = $.fn.tree.extensions.initExtensions = function (t, opts) {
        var exts = opts._extensions ? opts._extensions : opts._extensions = {};
        if (exts._initialized) { return; }
        initAutoToggle(t, opts, exts);
        exts._initialized = true;
    };
    
    
    
    
        var loader = $.fn.tree.extensions.loader = function (param, success, error) {
        var t = $(this), opts = t.tree("options");
        initExtensions(t, opts);
        if (!opts.url) { return false; }
        param = parseQueryParams(opts, param);
        $.ajax({
            type: opts.method, url: opts.url, data: param, dataType: "json",
            success: function (data) { success(data); },
            error: function () { error.apply(this, arguments); }
        });
    };
    
       var defaults = $.fn.tree.extensions.defaults = {
			 //  扩展 easyui-tree 的自定义属性，表示当左键点击带有子节点的条目时，是否自动展开/折叠相应节点。
        //  Boolean 类型，默认为 false。
        //  备注：该功能不会影响到 easyui-tree 的原生事件 onClick。
        
        toggleOnClick: false,
         //  覆盖 easyui-tree 的原生事件 onExpand，以支持相应扩展功能。
        onExpand: onExpand,
        
            //  扩展 easyui-tree 的自定义属性，表示同一级菜单节点下，只允许一个节点被展开。
        //  Boolean 类型，默认为 false。
        //  当该属性设置为 true 时，建议同时把 animate 属性设置为 false，以免影响菜单联动折叠时的美观效果。
        onlyNodeExpand: false,
        
        
        //  覆盖 easyui-tree 的原生属性 loader，以支持相应扩展功能。
        loader: loader,
        
        //增加是否点击子节点打开相应面板
        leafClickOpen:false
        
       };

    $.extend($.fn.tree.defaults, defaults);
    $.extend($.fn.tree.methods, methods);
})(jQuery);