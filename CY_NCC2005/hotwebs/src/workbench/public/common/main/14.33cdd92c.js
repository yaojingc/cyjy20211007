(window.webpackJsonp=window.webpackJsonp||[]).push([[14],{1285:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=m(a(142)),i=m(a(113)),o=m(a(12)),r=m(a(4)),l=m(a(8)),s=m(a(5)),c=m(a(6)),d=m(a(914));a(912);var u=a(1),f=m(u),p=a(902),h=m(a(427));function m(e){return e&&e.__esModule?e:{default:e}}var g=m(a(33)).default.base.NCDiv,v=d.default.TreeNode,b=function(e){function t(e){(0,r.default)(this,t);var a=(0,s.default)(this,(t.__proto__||(0,o.default)(t)).call(this,e));return a.onExpand=function(e){e.push("00"),e=(0,i.default)(new n.default(e)),a.setState({expandedKeys:e,autoExpandParent:!1})},a.onChange=function(e){var t=e.target.value;a.setState({searchValue:t},(function(){a.props.onSearch(t,a.handleExpanded)}))},a.handleExpanded=function(e){var t=e.map((function(e,t){return e.code}));t.push("00"),t=(0,i.default)(new n.default(t)),a.setState({expandedKeys:t,autoExpandParent:!0})},a.handleSelect=function(e,t){0!==e.length&&a.props.onSelect(e[0])},a.state={expandedKeys:["00"],searchValue:"",autoExpandParent:!0},a}return(0,c.default)(t,e),(0,l.default)(t,[{key:"render",value:function(){var e=this,t=this.state,a=t.searchValue,n=t.expandedKeys,i=t.autoExpandParent,o=[{name:this.props.rootTitle,code:"00",children:(0,p.createTree)(this.props.dataSource,"code","parentcode")}];return f.default.createElement(g,{className:"individuation-tree-search workbench-scroll",areaCode:g.config.TreeCom,fieldid:"individuation"},f.default.createElement(d.default,{showLine:!0,showIcon:!0,onExpand:this.onExpand,expandedKeys:n,onSelect:this.handleSelect,selectedKeys:this.props.selectedKeys,autoExpandParent:i},function t(n){return n.map((function(n){var i=n.code,o=n.name,r=void 0,l=(r="00"===i?""+o:i+" "+o).indexOf(a),s=r.substr(0,l),c=r.substr(l+a.length),d=l>-1?f.default.createElement("span",null,s,f.default.createElement("span",{style:{color:"#f50"}},a),c):f.default.createElement("span",null,r),u=e.props.isDisabled&&n.code!==e.props.selectedKeys[0],p="00"===i?"fieldid_"+o+"_node":"fieldid_"+i+"_node";return n.children?f.default.createElement(v,{icon:function(e){var t=e.expanded;return f.default.createElement(h.default,{width:20,height:20,xlinkHref:t?"#icon-wenjianjiadakai":"#icon-wenjianjia"})},switcherIcon:function(e){var t=e.expanded;return f.default.createElement("i",{className:"font-size-18 iconfont "+(t?"icon-shu_zk":"icon-shushouqi")})},key:i,title:d,disabled:u,className:p},t(n.children)):f.default.createElement(v,{icon:f.default.createElement("span",{className:"tree-dot"}),key:i,title:d,disabled:u,className:p})}))}(o)))}}]),t}(u.Component);t.default=b},1286:function(e,t,a){var n=a(1287);"string"==typeof n&&(n=[[e.i,n,""]]);var i={hmr:!0,transform:void 0,insertInto:void 0};a(15)(n,i);n.locals&&(e.exports=n.locals)},1287:function(e,t,a){(e.exports=a(13)(!1)).push([e.i,".nc-workbench-page .nc-workbench-page-header>div{color:#111;font-size:16px;font-weight:500}.nc-workbench-page .nc-workbench-individuation{overflow:hidden}.nc-workbench-page .nc-workbench-individuation .nc-workbench-ownpage-left .individuation-tree-search{padding:20px;height:100%}.nc-workbench-page .nc-workbench-individuation .nc-workbench-individuation-form{padding:16px 10px;min-width:300px}.nc-workbench-page .nc-workbench-individuation .nc-workbench-individuation-form .ant-form{padding:24px;background:#fbfbfb;border-radius:6px}",""])},888:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=E(a(423)),i=E(a(39)),o=E(a(2)),r=E(a(12)),l=E(a(4)),s=E(a(8)),c=E(a(5)),d=E(a(6));a(424);var u=a(1),f=E(u),p=E(a(90)),h=a(895),m=E(a(1285)),g=E(a(903)),v=a(900),b=E(a(26)),k=E(a(58)),w=a(433),y=a(196),x=a(17),_=E(a(906));function E(e){return e&&e.__esModule?e:{default:e}}a(1286);var C=E(a(33)).default.base.NCDiv,P=function(e){function t(e){(0,l.default)(this,t);var a=(0,c.default)(this,(t.__proto__||(0,r.default)(t)).call(this,e));return a.handleBtnClick=function(e){switch(e){case"add":a.setState({isedit:!0,isNew:!0,fields:(0,o.default)({},a.newFormData),formData:(0,o.default)({},a.newFormData)});break;case"edit":var t=a.props.form.getFieldsValue();a.historyData=(0,o.default)({},a.state.fields,t),a.setState({isedit:!0,formData:t,fields:a.historyData});break;case"save":a.save();break;case"cancle":(0,w.CancelPrompts)((function(){a.props.form.resetFields(),a.setState({isedit:!1,isNew:!1,fields:(0,o.default)({},a.historyData),formData:{code:a.historyData.code,name:a.historyData.name,resourceid:a.historyData.resourceid,resourcepath:a.historyData.resourcepath,page_part_url:a.historyData.page_part_url}})}));break;case"del":a.del()}},a.del=function(){var e=a.state.langMultiData;(0,w.DelPrompts)((function(){var t=a.state.fields.pk_individualreg;(0,b.default)({url:"/nccloud/platform/appregister/deleteindividualreg.do",info:{name:(0,x.langCheck)("1022PREGI-000001",!0,e),action:(0,x.langCheck)("1022PREGI-000002",!0,e)},data:{pk_individualreg:t},success:function(e){var n=e.data.data;if(n){var o=a.state.treeData;o=[].concat((0,i.default)(a.state.treeData)),p.default.remove(o,(function(e){return e.pk_individualreg===t})),a.setState({treeData:o,parentKey:"",selectedKeys:["00"]}),(0,k.default)({status:"success",msg:n.true})}}})}))},a.save=function(){var e=a.state.langMultiData;a.props.form.validateFields((function(t){if(!t){var n=a.state,r=n.isNew,l=n.fields,s=void 0,c=void 0,d=a.props.form.getFieldsValue(),u=(0,o.default)({},l,d);r?(s="/nccloud/platform/appregister/insertindividualreg.do",c=u):(s="/nccloud/platform/appregister/editindividualreg.do",c=u),(0,b.default)({url:s,info:{name:(0,x.langCheck)("1022PREGI-000001",!0,e),action:(0,x.langCheck)("1022PREGI-000003",!0,e)},data:c,success:function(t){var n=t.data.data;if(n){var s=[].concat((0,i.default)(a.state.treeData));if(r)s=p.default.concat(s,n),u=n;else s[p.default.findIndex(s,(function(e){return e.pk_individualreg===l.pk_individualreg}))]=u;a.setState({isNew:!1,isedit:!1,selectedKeys:[l.code],parentKey:l.code,treeData:s,fields:u,formData:(0,o.default)({},d)},(function(){a.handleSelect(u.code)})),(0,k.default)({status:"success",msg:n.true?n.true:(0,x.langCheck)("1022PREGI-000004",!0,e)})}}})}}))},a.handleSelect=function(e){if(!a.treeNodeChange()){var t=a.state,n=t.treeData,i=t.selectedKeys;if("00"===e||void 0===e)return i=["00"],void a.setState({isedit:!1,selectedKeys:i,parentKey:e||"",fields:(0,o.default)({},a.newFormData),formData:(0,o.default)({},a.newFormData)});i=[e];var r=n.find((function(t){return t.code===e}));a.historyData=(0,o.default)({},r),a.setState({isedit:!1,isNew:!1,selectedKeys:i,parentKey:e,fields:(0,o.default)({},r),formData:{code:r.code,name:r.name,resourceid:r.resourceid,resourcepath:r.resourcepath,page_part_url:r.page_part_url}})}},a.treeNodeChange=function(){var e=!0;return a.state.isedit?(0,w.SavePrompts)((function(){a.save(),e=!1})):e=!1,e},a.handleFormChange=function(e){a.setState((function(t){var a=t.fields;return{fields:(0,o.default)({},a,e)}}))},a.state={treeData:[],fields:{},formData:{},isNew:!1,isedit:!1,parentKey:"",selectedKeys:["00"],langMultiData:{}},a.newFormData={code:"",name:"",resourceid:"",resourcepath:"",page_part_url:""},a.historyData,a}return(0,d.default)(t,e),(0,s.default)(t,[{key:"componentDidMount",value:function(){var e=this,t=this.state.langMultiData;(0,y.getMulti)({moduleId:"1022PREGI",domainName:"workbench",callback:function(t){e.setState({langMultiData:t})}}),(0,b.default)({url:"/nccloud/platform/appregister/queryindividualreg.do",info:{name:(0,x.langCheck)("1022PREGI-000001",!0,t),action:(0,x.langCheck)("1022PREGI-000005",!0,t)},success:function(t){var a=t.data,n=a.success,i=a.data;n&&i&&e.setState({treeData:i},(function(){i.length>0&&e.handleSelect(i[0].code)}))}})}},{key:"render",value:function(){var e=this.state.langMultiData,t=this.state,a=t.treeData,n=t.formData,i=(t.fields,t.isedit),o=t.isNew,r=t.selectedKeys,l=n.code,s=n.name,c=n.resourceid,d=n.resourcepath,u=n.page_part_url,p=[{code:"code",type:"string",label:(0,x.langCheck)("1022PREGI-000006",!0,e),isRequired:!0,isedit:i,initialValue:l,xs:24,md:12,lg:12},{code:"name",type:"string",label:(0,x.langCheck)("1022PREGI-000007",!0,e),isRequired:!0,isedit:i,initialValue:s,xs:24,md:12,lg:12},{code:"resourceid",type:"string",label:(0,x.langCheck)("1022PREGI-000008",!0,e),isRequired:!0,isedit:i,initialValue:c,xs:24,md:12,lg:12},{code:"resourcepath",type:"string",label:(0,x.langCheck)("1022PREGI-000009",!0,e),isRequired:!0,isedit:i,initialValue:d,xs:24,md:12,lg:12},{code:"page_part_url",type:"string",label:(0,x.langCheck)("1022PREGI-000010",!0,e),isRequired:!0,initialValue:u,isedit:i}],b=[{name:(0,x.langCheck)("1022PREGI-000011",!0,e),code:"add",type:"primary",isshow:(""===this.state.parentKey||"00"===this.state.parentKey)&&!i},{name:(0,x.langCheck)("1022PREGI-000012",!0,e),code:"edit",type:"primary",group:!0,isshow:""!==this.state.parentKey&&"00"!==this.state.parentKey&&!i},{name:(0,x.langCheck)("1022PREGI-000002",!0,e),code:"del",group:!0,isshow:""!==this.state.parentKey&&"00"!==this.state.parentKey&&!i},{name:(0,x.langCheck)("1022PREGI-000003",!0,e),code:"save",type:"primary",isshow:i},{name:(0,x.langCheck)("1022PREGI-000013",!0,e),code:"cancle",isshow:i}],k=(0,x.GetQuery)(window.location.hash).c;return f.default.createElement(h.PageLayout,{className:"nc-workbench-individuation",header:f.default.createElement(h.PageLayoutHeader,null,f.default.createElement("div",{className:"header_in_icon"},f.default.createElement(_.default,{appCode:k}),f.default.createElement(C,{fieldid:(0,x.langCheck)("1022PREGI-000001","pages",e),areaCode:C.config.Title},(0,x.langCheck)("1022PREGI-000001",!0,e))),f.default.createElement(g.default,{dataSource:b,onClick:this.handleBtnClick}))},f.default.createElement(h.PageLayoutLeft,null,f.default.createElement(m.default,{isDisabled:this.state.isedit,selectedKeys:r,onSelect:this.handleSelect,dataSource:a,rootTitle:(0,x.langCheck)("1022PREGI-000000",!0,e)})),f.default.createElement(h.PageLayoutRight,{className:"workbench-auto-scroll"},f.default.createElement("div",{className:"nc-workbench-individuation-form"},""!==this.state.parentKey&&"00"!==this.state.parentKey||o?f.default.createElement(v.FormContent,{datasources:(0,v.dataDefaults)(this.state.formData,p,"code"),form:this.props.form,formData:p,fieldid:"individuation"}):"")))}}]),t}(u.Component);P=n.default.create()(P),t.default=P},895:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.PageLayoutRight=t.PageLayoutLeft=t.PageLayoutHeader=t.PageScrollLayout=t.PageLayout=void 0;var n,i,o,r,l,s,c,d,u,f,p=y(a(12)),h=y(a(4)),m=y(a(8)),g=y(a(5)),v=y(a(6)),b=a(1),k=y(b),w=y(a(0));function y(e){return e&&e.__esModule?e:{default:e}}a(896);var x=y(a(33)).default.base.NCDiv,_=!1,E=(i=n=function(e){function t(e){(0,h.default)(this,t);var a=(0,g.default)(this,(t.__proto__||(0,p.default)(t)).call(this,e));return a.handleMouseUp=function(){_=!1},a.handleMouseMove=function(e){if(_){var t=e.clientX,a=document.querySelector("#layoutLeft"),n=a.getBoundingClientRect().left,i=parseInt(t-n)+3;i<200||(a.style.width=i+"px")}},a}return(0,v.default)(t,e),(0,m.default)(t,[{key:"render",value:function(){return k.default.createElement("div",{className:"nc-workbench-page"},this.props.header?this.props.header:null,k.default.createElement("div",{onMouseMove:this.handleMouseMove,onMouseUp:this.handleMouseUp,className:"nc-workbench-ownpage "+(2===this.props.children.length?"nc-workbench-ownpage-all":"")+" "+this.props.className},this.props.children))}}]),t}(b.Component),n.defaultProps={className:""},i),C=(r=o=function(e){function t(e){(0,h.default)(this,t);var a=(0,g.default)(this,(t.__proto__||(0,p.default)(t)).call(this,e));return a.handleMouseUp=function(){_=!1},a.handleMouseMove=function(e){if(_){var t=e.clientX,a=document.querySelector("#layoutLeft"),n=a.getBoundingClientRect().left,i=parseInt(t-n)+3;i<200||(a.style.width=i+"px")}},a.handleScroll=function(e){var t=a.refs.ncWorkbenchPageScroll.getBoundingClientRect(),n=t.y;t.width;if(n==a.initScrollContainerTop&&a.setState({suck:!1}),n<a.initScrollContainerTop){if(a.state.suck)return;a.containerWidthChange(),a.setState({suck:!0})}},a.containerWidthChange=function(){var e=a.refs.ncWorkbenchPageOwnpage.getBoundingClientRect().width;document.getElementsByClassName("nc-workbench-page-header")[0].style.width=e+"px",document.getElementById("suckTableHeader")&&(document.getElementById("suckTableHeader").style.width=e+"px")},a.state={suck:!1},a.initScrollContainerTop,a}return(0,v.default)(t,e),(0,m.default)(t,[{key:"componentDidMount",value:function(){window.addEventListener("resize",this.containerWidthChange);var e=this.refs.ncWorkbenchPageScroll.getBoundingClientRect().y;this.initScrollContainerTop=e}},{key:"componentWillUnmount",value:function(){window.removeEventListener("resize",this.containerWidthChange)}},{key:"render",value:function(){var e=this.state.suck;return k.default.createElement("div",{className:"nc-workbench-page-scroll",onScroll:this.handleScroll},k.default.createElement("div",{className:" nc-workbench-page "},k.default.createElement("div",{className:"nc-workbench-page-container "+(e?"nc-workbench-suck":""),ref:"ncWorkbenchPageScroll"},this.props.header?this.props.header:null,k.default.createElement("div",{onMouseMove:this.handleMouseMove,onMouseUp:this.handleMouseUp,ref:"ncWorkbenchPageOwnpage",className:"nc-workbench-ownpage "+(2===this.props.children.length?"nc-workbench-ownpage-all":"")+" "+this.props.className},this.props.children))))}}]),t}(b.Component),o.defaultProps={className:""},r),P=(s=l=function(e){function t(e){(0,h.default)(this,t);var a=(0,g.default)(this,(t.__proto__||(0,p.default)(t)).call(this,e));return a.handleMouseDown=function(){_=!0},a}return(0,v.default)(t,e),(0,m.default)(t,[{key:"render",value:function(){return k.default.createElement("div",{id:"layoutLeft",className:"nc-workbench-ownpage-left "+this.props.className},k.default.createElement("span",{className:"layout-drag-block",onMouseDown:this.handleMouseDown}),this.props.children)}}]),t}(b.Component),l.defaultProps={className:""},s),M=(d=c=function(e){function t(e){return(0,h.default)(this,t),(0,g.default)(this,(t.__proto__||(0,p.default)(t)).call(this,e))}return(0,v.default)(t,e),(0,m.default)(t,[{key:"render",value:function(){return k.default.createElement(x,{areaCode:x.config.HEADER,className:"nc-workbench-page-header "+this.props.className},this.props.children)}}]),t}(b.Component),c.defaultProps={className:""},d),N=(f=u=function(e){function t(){return(0,h.default)(this,t),(0,g.default)(this,(t.__proto__||(0,p.default)(t)).apply(this,arguments))}return(0,v.default)(t,e),(0,m.default)(t,[{key:"render",value:function(){return k.default.createElement("div",{className:"nc-workbench-ownpage-right "+this.props.className},this.props.children)}}]),t}(b.Component),u.defaultProps={className:""},f);E.propTypes={children:w.default.any.isRequired},P.propTypes={children:w.default.any.isRequired},N.propTypes={children:w.default.any.isRequired},t.PageLayout=E,t.PageScrollLayout=C,t.PageLayoutHeader=M,t.PageLayoutLeft=P,t.PageLayoutRight=N},896:function(e,t,a){var n=a(897);"string"==typeof n&&(n=[[e.i,n,""]]);var i={hmr:!0,transform:void 0,insertInto:void 0};a(15)(n,i);n.locals&&(e.exports=n.locals)},897:function(e,t,a){(e.exports=a(13)(!1)).push([e.i,'.nc-workbench-page{position:absolute;top:0;z-index:10;height:100%;width:100%;padding:8px 0 0;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column}.nc-workbench-page .nc-workbench-page-header{-webkit-box-flex:0;flex:0 0 45px;display:-webkit-box;display:flex;-webkit-box-pack:justify;justify-content:space-between;-webkit-box-align:center;align-items:center;border-bottom:1px solid #d9d9d9;border-radius:3px 3px 0 0;background:#fff;padding:0 20px}.nc-workbench-page .nc-workbench-page-header .pageLayoutHeaderOwn{font-size:16px;color:#111;font-weight:700}.nc-workbench-page .nc-workbench-page-header .pageLayoutHeaderOwn .iconfont{margin-right:6px;border-radius:50%;font-size:16px;padding:4px;font-weight:400;cursor:pointer}.nc-workbench-page .nc-workbench-page-header .pageLayoutHeaderOwn .iconfont:hover{background:#e8e8e8}.nc-workbench-page .nc-workbench-page-header .pageLayoutHeaderOwn span[type=ncdiv]{font-size:inherit}.nc-workbench-page .nc-workbench-page-header .header_in_icon{display:-webkit-box;display:flex;font-weight:700;font-size:16px;color:#111;line-height:24px}.nc-workbench-page .nc-workbench-page-header .header_in_icon span[type=ncdiv]{font-size:inherit}.nc-workbench-page .nc-workbench-ownpage{min-width:800px;border-radius:0 0 3px 3px}.nc-workbench-page .nc-workbench-ownpage-all{height:100%;display:-webkit-box;display:flex;-webkit-box-pack:start;justify-content:flex-start;background:#fff;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}.nc-workbench-page .nc-workbench-ownpage-left{position:relative;height:100%;min-width:280px}.nc-workbench-page .nc-workbench-ownpage-left .layout-drag-block{position:absolute;right:-3px;z-index:10;top:50%;-webkit-transform:translateY(-50%);transform:translateY(-50%);display:block;padding:0 3px;height:100%;border-radius:3px;cursor:w-resize}.nc-workbench-page .nc-workbench-ownpage-left .layout-drag-block:after{content:"";display:block;width:1px;height:100%;background:#d9d9d9}.nc-workbench-page .nc-workbench-ownpage-left .layout-drag-block:hover:after{width:2px;background:rgba(0,0,0,.54)}.nc-workbench-page .nc-workbench-ownpage-right{min-width:50%;-webkit-box-flex:1;flex:1}.nc-workbench-page-scroll{height:100%;width:100%;overflow-y:auto}.nc-workbench-page-scroll .nc-workbench-page-container{margin-top:0;min-height:100%;background:#fff;border-radius:3px}.nc-workbench-page-scroll .nc-workbench-page-container .nc-workbench-page-header{position:static;-webkit-transition:top .7s ease;transition:top .7s ease;height:46px}.nc-workbench-page-scroll .nc-workbench-page-container .suck-table-header{position:static;-webkit-transition:top .7s ease;transition:top .7s ease}.nc-workbench-page-scroll .nc-workbench-suck{padding-top:80px}.nc-workbench-page-scroll .nc-workbench-suck .nc-workbench-page-header{position:fixed;z-index:1;top:44px}.nc-workbench-page-scroll .nc-workbench-suck .suck-table-header{position:fixed;z-index:1;top:94px}@media screen and (min-width:1280px){.nc-workbench-page{padding:8px 16px 0}}@media screen and (min-width:1440px){.nc-workbench-page{padding:8px 16px 0}}@media screen and (min-width:1600px){.nc-workbench-page{padding:8px 16px 0}}',""])},900:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.dataDefaults=t.FormContent=void 0;var n=w(a(65)),i=w(a(907)),o=w(a(908)),r=w(a(12)),l=w(a(4)),s=w(a(8)),c=w(a(5)),d=w(a(6)),u=w(a(423));a(909),a(910),a(424);var f=a(1),p=w(f),h=w(a(90)),m=w(a(922)),g=w(a(927)),v=w(a(928)),b=w(a(929)),k=a(17);function w(e){return e&&e.__esModule?e:{default:e}}a(930);var y=w(a(33)).default.base,x=y.NCCheckbox,_=y.NCDiv,E=u.default.Item,C=function(e){function t(e){return(0,l.default)(this,t),(0,c.default)(this,(t.__proto__||(0,r.default)(t)).call(this,e))}return(0,d.default)(t,e),(0,s.default)(t,[{key:"render",value:function(){var e=this.props.value;return p.default.createElement("div",{className:"form-display-content",title:e},this.props.value)}}]),t}(f.Component),P=function(e){function t(e){return(0,l.default)(this,t),(0,c.default)(this,(t.__proto__||(0,r.default)(t)).call(this,e))}return(0,d.default)(t,e),(0,s.default)(t,[{key:"render",value:function(){var e=this.props.value&&this.props.value.refname?this.props.value.refname:"";return p.default.createElement("div",{className:"form-display-content",title:e},e)}}]),t}(f.Component),M=function(e){function t(e){(0,l.default)(this,t);var a=(0,c.default)(this,(t.__proto__||(0,r.default)(t)).call(this,e));return a.showValue=function(){var e=a.props,t=e.options,n=e.value,i=t.find((function(e){return e.value===n}));return i?i.text:""},a}return(0,d.default)(t,e),(0,s.default)(t,[{key:"render",value:function(){var e=this.showValue();return p.default.createElement("div",{className:"form-display-content",title:e},e)}}]),t}(f.Component),N=function(e){function t(e){return(0,l.default)(this,t),(0,c.default)(this,(t.__proto__||(0,r.default)(t)).call(this,e))}return(0,d.default)(t,e),(0,s.default)(t,[{key:"render",value:function(){var e=this.props.checked?(0,k.langCheck)("0000PUB-000009"):(0,k.langCheck)("0000PUB-000010");return p.default.createElement("div",{className:"form-display-content",title:e},e)}}]),t}(f.Component);t.FormContent=function(e){function t(e){(0,l.default)(this,t);var a=(0,c.default)(this,(t.__proto__||(0,r.default)(t)).call(this,e));return a.createComponent=function(e,t){var n=a.props.form.getFieldDecorator,i=e.type,o=void 0===i?"string":i,r=e.isRequired,l=void 0!==r&&r,s=e.label,c=void 0===s?"":s,d=e.code,u=e.isedit,f=void 0!==u&&u,h=e.check,w=e.len,y=e.initialValue;switch(o){case"string":return p.default.createElement(E,{className:"form-item margin-bottom-8",colon:!f,label:c},n(d,{initialValue:y,rules:[{type:"string",message:""+c+(0,k.langCheck)("0000PUB-000015")+"-string"},{required:l&&f,whitespace:!0,message:""+c+(0,k.langCheck)("0000PUB-000016")},{len:w,message:""+c+(0,k.langCheck)("0000PUB-000017")+w},{validator:h||function(e,t,a){a()}}]})(f?p.default.createElement(g.default,{form:a.props.form,fieldid:e.fieldid||d}):p.default.createElement(C,null)));case"refer":return p.default.createElement(E,{className:"form-item margin-bottom-8",colon:!f,label:c},n(d,{initialValue:y,rules:[{required:l&&f,validator:l?function(e,t,a){t&&t.refname?a():a(""+c+(0,k.langCheck)("0000PUB-000016"))}:function(e,t,a){a()}},{type:"object",message:""+c+(0,k.langCheck)("0000PUB-000015")+"-object",validator:null}]})(f?p.default.createElement(b.default,{form:a.props.form,options:e.options,fieldid:e.fieldid||d}):p.default.createElement(P,null)));case"select":return p.default.createElement(E,{className:"form-item margin-bottom-8",colon:!f,label:c},n(d,{initialValue:y,rules:[{type:"string",message:""+c+(0,k.langCheck)("0000PUB-000015")+"-string",validator:null},{required:l&&f,message:""+c+(0,k.langCheck)("0000PUB-000016")}]})(f?p.default.createElement(v.default,{form:a.props.form,placeholder:""+(0,k.langCheck)("0000PUB-000018")+c,options:e.options,fieldid:e.fieldid||d}):p.default.createElement(M,{options:e.options})));case"checkbox":return p.default.createElement(E,{colon:!f,label:c,className:"form-item margin-bottom-8"},n(d,{initialValue:y,valuePropName:"checked"})(f?p.default.createElement(x,{disabled:!f}):p.default.createElement(N,null)));case"chooseImage":return p.default.createElement(E,{label:c},n(d,{initialValue:y,rules:[{required:l&&f,message:(0,k.langCheck)("0000PUB-000011")}]})(p.default.createElement(m.default,{form:a.props.form,isedit:f,data:e.options,title:f?(0,k.langCheck)("0000PUB-000012"):(0,k.langCheck)("0000PUB-000013")})))}},a.createFormItem=function(){return a.props.formData.map((function(e,t){var n=e.xs,i=void 0===n?24:n,r=e.md,l=void 0===r?12:r,s=e.lg,c=void 0===s?12:s,d=e.xl,u=void 0===d?8:d,f=e.code,h=e.hidden;return!0===(void 0!==h&&h)?null:p.default.createElement(o.default,{xs:i,md:l,lg:c,xl:u,key:f,fieldid:e.fieldid||f},a.createComponent(e,t))}))},a.history,a}return(0,d.default)(t,e),(0,s.default)(t,[{key:"componentWillReceiveProps",value:function(){this.history=this.props.datasources}},{key:"componentDidMount",value:function(){this.props.form.setFieldsValue(this.props.datasources)}},{key:"componentDidUpdate",value:function(){h.default.isEqual(this.props.datasources,this.history)||this.props.form.setFieldsValue(this.props.datasources)}},{key:"render",value:function(){return p.default.createElement(_,{fieldid:this.props.fieldid,areaCode:_.config.FORM},p.default.createElement(u.default,{layout:"inline"},p.default.createElement(i.default,null,this.createFormItem())))}}]),t}(f.Component),t.dataDefaults=function(e,t){arguments.length>2&&void 0!==arguments[2]&&arguments[2];if("{}"!=(0,n.default)(t)){var a={};return t.map((function(t){e.hasOwnProperty(t.code)&&(a[t.code]=e[t.code])})),a}console.error((0,k.langCheck)("0000PUB-000014"))}},902:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.createTree=void 0;var n,i=a(90),o=(n=i)&&n.__esModule?n:{default:n};t.createTree=function(e,t,a){e=o.default.cloneDeep(e);var n=r(e,a),i=n.treeArray,c=n.treeObject;return c=l(c,t),s(i,c,t)};var r=function(e,t){var a=[],n={};return e.map((function(e,i){e[t]?n.hasOwnProperty(e[t])?n[e[t]].push(e):(n[e[t]]=[],n[e[t]].push(e)):a.push(e)})),{treeArray:a,treeObject:n}},l=function(e,t){for(var a in e)e.hasOwnProperty(a)&&(e[a]=s(e[a],e,t));return e},s=function(e,t,a){return e.map((function(e,n){return t.hasOwnProperty(e[a])&&(e.children=t[e[a]]),e}))}},903:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=d(a(12)),i=d(a(4)),o=d(a(8)),r=d(a(5)),l=d(a(6)),s=a(1),c=d(s);function d(e){return e&&e.__esModule?e:{default:e}}a(904);var u=d(a(33)).default.base,f=u.NCButtonGroup,p=u.NCButton,h=u.NCMenu,m=u.NCDropdown,g=function(e){function t(e){(0,i.default)(this,t);var a=(0,r.default)(this,(t.__proto__||(0,n.default)(t)).call(this,e));return a.createBtn=function(e){return e.map((function(e){if(e.isshow){if("btn"==e.divide)return c.default.createElement(p,{className:"divide-btn",key:e.code,colors:e.type,fieldid:e.fieldid||e.code,onClick:function(){a.props.onClick(e.code)}},e.name);if("select"==e.divide){var t=c.default.createElement(h,{onClick:function(){a.props.onClick(e.code)}},c.default.createElement(h.Item,{key:"1",fieldid:e.fieldid||e.code},c.default.createElement("div",null,e.name)));return c.default.createElement(m,{overlay:t,trigger:["click"],fieldid:e.dropdownFieldid},c.default.createElement(p,{className:"margin-left-1",key:e.code,colors:e.type,fieldid:e.fieldid||e.code,is_arrow:!0,style:{minWidth:18}},c.default.createElement("i",{className:"iconfont icon-hangcaozuoxiala1"})))}return c.default.createElement(p,{className:"margin-left-6 "+(e.className?e.className:""),key:e.code,colors:e.type,fieldid:e.fieldid||e.code,onClick:function(){a.props.onClick(e.code)}},e.name)}return null}))},a.createBtnGroup=function(e){return e.length>0?c.default.createElement(f,null,a.createBtn(e)):null},a.createButton=function(e){var t=e.filter((function(e){return e.group})),n=e.filter((function(e){return!e.group}));return c.default.createElement("div",null,a.createBtn(n),c.default.createElement("div",{style:{marginLeft:6}},a.createBtnGroup(t)))},a}return(0,l.default)(t,e),(0,o.default)(t,[{key:"render",value:function(){return c.default.createElement("div",{className:"buttons-component "+(this.props.className?this.props.className:"")},this.createButton(this.props.dataSource))}}]),t}(s.Component);t.default=g},904:function(e,t,a){var n=a(905);"string"==typeof n&&(n=[[e.i,n,""]]);var i={hmr:!0,transform:void 0,insertInto:void 0};a(15)(n,i);n.locals&&(e.exports=n.locals)},905:function(e,t,a){(e.exports=a(13)(!1)).push([e.i,".buttons-component div{display:-webkit-box;display:flex;-webkit-box-align:center;align-items:center}.buttons-component .ant-btn-group{margin-left:6px}body .divide-btn{margin-left:6px;border-top-right-radius:0;border-bottom-right-radius:0}body .margin-left-1{margin-left:1px;width:18px;border-top-left-radius:0;border-bottom-left-radius:0;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;padding:0}body .margin-left-1 .iconfont{font-size:12px}",""])},906:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=l(a(1)),i=l(a(198)),o=l(a(425)),r=a(34);function l(e){return e&&e.__esModule?e:{default:e}}t.default=function(e){var t=e.appCode,a=(0,r.getStore)("APPICONSSTORE"),l=a&&a[t],s=i.default[l]?i.default[l]:o.default;return n.default.createElement("div",{style:{width:"24px",height:"24px",marginRight:"10px",background:"url("+s+") no-repeat 0px 0px",backgroundSize:"contain"}})}},907:function(e,t,a){"use strict";a.r(t);var n=a(421);t.default=n.a},908:function(e,t,a){"use strict";a.r(t);var n=a(194);t.default=n.a},909:function(e,t,a){"use strict";a.r(t);a(25),a(426)},910:function(e,t,a){"use strict";a.r(t);a(25),a(426)},922:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=f(a(59)),i=f(a(12)),o=f(a(4)),r=f(a(8)),l=f(a(5)),s=f(a(6));a(75);var c=a(1),d=f(c),u=f(a(923));function f(e){return e&&e.__esModule?e:{default:e}}var p=function(e){function t(e){(0,o.default)(this,t);var a=(0,l.default)(this,(t.__proto__||(0,i.default)(t)).call(this,e));return a.handleChange=function(e){var t=a.props.onChange;t&&t(e)},a.handleMouseEnter=function(){a.setState({visible:!0})},a.handleMouseLeave=function(){a.setState({visible:!1})},a.state={visible:!1},a}return(0,s.default)(t,e),(0,r.default)(t,[{key:"render",value:function(){var e=this.props.form.getFieldError(this.props.id),t=!!e;return d.default.createElement(n.default,{placement:"top",overlayClassName:"tootip-white",visible:this.state.visible&&t,title:e&&e[e.length-1]},d.default.createElement("div",{onMouseEnter:this.handleMouseEnter,onMouseLeave:this.handleMouseLeave},d.default.createElement(u.default,this.props)))}}]),t}(c.Component);t.default=p},923:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n,i,o=v(a(12)),r=v(a(4)),l=v(a(8)),s=v(a(5)),c=v(a(6)),d=a(1),u=v(d),f=v(a(198)),p=v(a(924)),h=v(a(425)),m=a(17),g=v(a(422));function v(e){return e&&e.__esModule?e:{default:e}}a(925);var b=(i=n=function(e){function t(e){(0,r.default)(this,t);var a=(0,s.default)(this,(t.__proto__||(0,o.default)(t)).call(this,e));return a.showModal=function(){a.props.isedit&&a.setState({visible:!0})},a.handleOk=function(e){var t=a.state.value;a.value=t,a.setState({visible:!1},a.triggerChange(t))},a.handleCancel=function(e){var t=a.state,n=t.listData,i=t.value;i=a.value,n=a.updateListData(n,i),a.setState({visible:!1,value:i,listData:n})},a.handleSelect=function(e){var t=a.state,n=t.listData,i=t.value;i=e.value,n=a.updateListData(n,i),a.setState({listData:n,value:i})},a.triggerChange=function(e){var t=a.props.onChange;t&&t(e)},a.updateListData=function(e,t){return e.map((function(e,a){return e.iconList.map((function(e){return e.selected=!1,e.value===t&&(e.selected=!0),e})),e}))},a.state={visible:!1,selected:"",listData:[],value:""},a.value="",a}return(0,c.default)(t,e),(0,l.default)(t,[{key:"componentWillReceiveProps",value:function(e){var t=e.data,a=e.value;this.value=a;var n=this.updateListData(t,a);this.setState({listData:n,value:a})}},{key:"componentWillMount",value:function(){var e=this.props,t=e.data,a=e.value;this.value=a;var n=this.updateListData(t,a);this.setState({listData:n,value:a})}},{key:"render",value:function(){var e=this,t=this.state,a=t.listData,n=t.value;return n||(n=""),u.default.createElement("div",{className:"choose-imgae"},-1===n.indexOf("/")?u.default.createElement("div",{onClick:this.props.isedit?this.showModal:null,className:"choose-btn",style:n.length>0?{background:"url("+(f.default[n]?f.default[n]:h.default)+") 0px 0px /contain no-repeat"}:null},this.props.isedit?u.default.createElement("i",{className:"iconfont icon-tianjiayingyong font-size-80"}):null):u.default.createElement("div",{className:"choose-btn",style:n.length>0?{background:"url("+n+") 0px 0px /contain no-repeat"}:null,onClick:this.props.isedit?this.showModal:null},this.props.isedit?u.default.createElement("i",{className:"iconfont icon-tianjiayingyong font-size-80"}):null),u.default.createElement(g.default,{maskClosable:!1,className:"choose-imgae-modal",width:640,title:this.props.title,visible:this.state.visible,onOk:this.handleOk,onCancel:this.handleCancel,cancelText:(0,m.langCheck)("0000PUB-000000"),okText:(0,m.langCheck)("0000PUB-000001"),fieldid:"chooseImage"},u.default.createElement("div",{className:"choose-imgae-container"},a.map((function(t){return u.default.createElement(p.default,{iconListData:t,onSelected:e.handleSelect})})))))}}]),t}(d.Component),n.defaultProps={isedit:!0},i);t.default=b},924:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n,i,o=p(a(12)),r=p(a(4)),l=p(a(8)),s=p(a(5)),c=p(a(6)),d=a(1),u=p(d),f=p(a(198));function p(e){return e&&e.__esModule?e:{default:e}}var h=(i=n=function(e){function t(e){(0,r.default)(this,t);var a=(0,s.default)(this,(t.__proto__||(0,o.default)(t)).call(this,e));return a.handleSelect=function(e){a.props.onSelected(e)},a.createIconList=function(e){return e.map((function(e){var t=e.name,n=e.src,i=e.selected;return u.default.createElement("li",{key:n},u.default.createElement("div",{title:t,onClick:function(){a.handleSelect(e)}},-1===n.indexOf("/")?u.default.createElement("div",{className:"icon "+(i?"img-selected":""),style:{background:"url("+f.default[n]+") no-repeat 0px 0px",backgroundSize:"contain"},fieldid:n}):u.default.createElement("img",{width:"80",src:n,alt:t}),u.default.createElement("div",{className:"icon-title"},t)))}))},a}return(0,c.default)(t,e),(0,l.default)(t,[{key:"render",value:function(){var e=this.props.iconListData,t=e.title,a=e.iconList;return u.default.createElement("div",{className:"choose-imgae-list"},u.default.createElement("div",{className:"title"},t),u.default.createElement("ul",null,this.createIconList(a)))}}]),t}(d.Component),n.defaultProps={isedit:!0},i);t.default=h},925:function(e,t,a){var n=a(926);"string"==typeof n&&(n=[[e.i,n,""]]);var i={hmr:!0,transform:void 0,insertInto:void 0};a(15)(n,i);n.locals&&(e.exports=n.locals)},926:function(e,t,a){(e.exports=a(13)(!1)).push([e.i,'.choose-imgae .choose-btn{border:1px solid #d9d9d9;width:100px;height:100px;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;color:#d9d9d9}.choose-imgae .choose-btn .font-size-80{font-size:80px}.choose-imgae .icon-image{height:100%;width:100%}.has-error .choose-imgae .choose-btn{border:1px solid #f5222d}.choose-imgae-modal .u-modal-body{padding:0}.choose-imgae-modal .choose-imgae-container{height:400px;overflow:auto;padding:24px}.choose-imgae-modal .choose-imgae-container .choose-imgae-list{display:-webkit-box;display:flex;-webkit-box-pack:start;justify-content:flex-start;-webkit-box-align:center;align-items:center;margin-top:20px}.choose-imgae-modal .choose-imgae-container .choose-imgae-list:first-child{margin-top:0}.choose-imgae-modal .choose-imgae-container .choose-imgae-list .title{color:#111;padding:10px}.choose-imgae-modal .choose-imgae-container .choose-imgae-list ul{display:-webkit-box;display:flex;-webkit-box-pack:start;justify-content:flex-start;flex-wrap:wrap}.choose-imgae-modal .choose-imgae-container .choose-imgae-list ul li{margin:6px}.choose-imgae-modal .choose-imgae-container .choose-imgae-list ul li .icon{height:40px;width:40px;overflow:hidden}.choose-imgae-modal .choose-imgae-container .choose-imgae-list ul li .icon-title{line-height:20px;font-size:12px;text-align:center}.choose-imgae-modal .choose-imgae-container .choose-imgae-list .img-selected{font-family:iconfont!important;position:relative;color:#52c41a;font-size:20px;overflow:unset!important}.choose-imgae-modal .choose-imgae-container .choose-imgae-list .img-selected:after{position:absolute;z-index:10;right:-5px;bottom:-5px;content:"\\E689";display:block;height:20px;width:20px;line-height:20px;text-align:center;background-color:#fff;border-radius:9px}',""])},927:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=f(a(59)),i=f(a(139)),o=f(a(12)),r=f(a(4)),l=f(a(8)),s=f(a(5)),c=f(a(6));a(75),a(195);var d=a(1),u=f(d);function f(e){return e&&e.__esModule?e:{default:e}}var p=function(e){function t(e){(0,r.default)(this,t);var a=(0,s.default)(this,(t.__proto__||(0,o.default)(t)).call(this,e));return a.handleChange=function(e){var t=a.props.onChange;t&&t(e)},a.handleMouseEnter=function(){a.setState({visible:!0})},a.handleMouseLeave=function(){a.setState({visible:!1})},a.state={visible:!1},a}return(0,c.default)(t,e),(0,l.default)(t,[{key:"render",value:function(){var e=this.props,t=e.value,a=e.form.getFieldError(this.props.id),o=!!a;return u.default.createElement(n.default,{placement:"top",overlayClassName:"tootip-white",visible:this.state.visible&&o,title:a&&a[a.length-1]},u.default.createElement("div",{style:{height:"30px"},onMouseEnter:this.handleMouseEnter,onMouseLeave:this.handleMouseLeave},u.default.createElement(i.default,{value:t,onChange:this.handleChange,onMouseOut:this.handleMouseOut,onMouseOver:this.handleMouseOver})))}}]),t}(d.Component);t.default=p},928:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=u(a(59)),i=u(a(12)),o=u(a(4)),r=u(a(8)),l=u(a(5)),s=u(a(6));a(75);var c=a(1),d=u(c);function u(e){return e&&e.__esModule?e:{default:e}}var f=u(a(33)).default.base.NCSelect,p=f.NCOption,h=function(e){function t(e){(0,o.default)(this,t);var a=(0,l.default)(this,(t.__proto__||(0,i.default)(t)).call(this,e));return a.createOption=function(e){return e.map((function(e,t){return d.default.createElement(p,{title:e.text,key:e.value,value:e.value},e.text)}))},a.handleChange=function(e){var t=a.props.onChange;t&&t(e)},a.handleMouseEnter=function(){a.setState({visible:!0})},a.handleMouseLeave=function(){a.setState({visible:!1})},a.state={visible:!1},a}return(0,s.default)(t,e),(0,r.default)(t,[{key:"render",value:function(){var e=this.props,t=e.value,a=e.form,i=e.placeholder,o=a.getFieldError(this.props.id),r=!!o;return d.default.createElement(n.default,{placement:"top",overlayClassName:"tootip-white",visible:this.state.visible&&r,title:o&&o[o.length-1]},d.default.createElement("div",{style:{height:"30px"},onMouseEnter:this.handleMouseEnter,onMouseLeave:this.handleMouseLeave},d.default.createElement(f,{value:t,placeholder:i,showClear:!1,fieldid:this.props.fieldid,onChange:this.handleChange},this.createOption(this.props.options))))}}]),t}(c.Component);t.default=h},929:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=f(a(59)),i=f(a(2)),o=f(a(12)),r=f(a(4)),l=f(a(8)),s=f(a(5)),c=f(a(6));a(75);var d=a(1),u=f(d);function f(e){return e&&e.__esModule?e:{default:e}}var p=a(874).high.Refer,h=function(e){function t(e){(0,r.default)(this,t);var a=(0,s.default)(this,(t.__proto__||(0,o.default)(t)).call(this,e));return a.handleChange=function(e){var t=a.props.onChange;t&&t(e)},a.handleMouseEnter=function(){a.setState({visible:!0})},a.handleMouseLeave=function(){a.setState({visible:!1})},a.state={visible:!1},a}return(0,c.default)(t,e),(0,l.default)(t,[{key:"render",value:function(){var e=this.props,t=e.value,a=e.form,o=e.options,r=a.getFieldError(this.props.id),l=!!r;return u.default.createElement(n.default,{placement:"top",overlayClassName:"tootip-white",visible:this.state.visible&&l,title:r&&r[r.length-1]},u.default.createElement("div",{style:{height:"30px"},onMouseEnter:this.handleMouseEnter,onMouseLeave:this.handleMouseLeave},u.default.createElement(p,(0,i.default)({value:t,fieldid:this.props.fieldid,onChange:this.handleChange},o))))}}]),t}(d.Component);t.default=h},930:function(e,t,a){var n=a(931);"string"==typeof n&&(n=[[e.i,n,""]]);var i={hmr:!0,transform:void 0,insertInto:void 0};a(15)(n,i);n.locals&&(e.exports=n.locals)},931:function(e,t,a){(e.exports=a(13)(!1)).push([e.i,".ant-form-item{display:-webkit-box!important;display:flex!important}.ant-form-item-label{min-width:150px}.ant-form-item-control-wrapper{-webkit-box-flex:1;flex:1;width:0}.ant-form-item-control-wrapper .form-display-content{height:30px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis}.ant-form-item-control-wrapper .u-checkbox-label{display:initial}",""])}}]);