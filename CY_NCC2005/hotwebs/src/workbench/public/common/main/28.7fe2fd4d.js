(window.webpackJsonp=window.webpackJsonp||[]).push([[28],{877:function(e,n,t){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var a=p(t(12)),o=p(t(4)),r=p(t(8)),c=p(t(5)),i=p(t(6)),l=t(1),s=p(l),d=p(t(895));function p(e){return e&&e.__esModule?e:{default:e}}var u=function(e){function n(e){return(0,o.default)(this,n),(0,c.default)(this,(n.__proto__||(0,a.default)(n)).call(this,e))}return(0,i.default)(n,e),(0,r.default)(n,[{key:"render",value:function(){var e=this.props.location;return s.default.createElement(d.default,null,s.default.createElement("h3",null,"Not Found ",s.default.createElement("code",null,e.pathname)))}}]),n}(l.Component);n.default=u},895:function(e,n,t){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.PageLayoutRight=n.PageLayoutLeft=n.PageLayoutHeader=n.PageScrollLayout=n.PageLayout=void 0;var a,o,r,c,i,l,s,d,p,u,h=y(t(12)),f=y(t(4)),g=y(t(8)),w=y(t(5)),b=y(t(6)),k=t(1),m=y(k),x=y(t(0));function y(e){return e&&e.__esModule?e:{default:e}}t(896);var v=y(t(33)).default.base.NCDiv,_=!1,M=(o=a=function(e){function n(e){(0,f.default)(this,n);var t=(0,w.default)(this,(n.__proto__||(0,h.default)(n)).call(this,e));return t.handleMouseUp=function(){_=!1},t.handleMouseMove=function(e){if(_){var n=e.clientX,t=document.querySelector("#layoutLeft"),a=t.getBoundingClientRect().left,o=parseInt(n-a)+3;o<200||(t.style.width=o+"px")}},t}return(0,b.default)(n,e),(0,g.default)(n,[{key:"render",value:function(){return m.default.createElement("div",{className:"nc-workbench-page"},this.props.header?this.props.header:null,m.default.createElement("div",{onMouseMove:this.handleMouseMove,onMouseUp:this.handleMouseUp,className:"nc-workbench-ownpage "+(2===this.props.children.length?"nc-workbench-ownpage-all":"")+" "+this.props.className},this.props.children))}}]),n}(k.Component),a.defaultProps={className:""},o),N=(c=r=function(e){function n(e){(0,f.default)(this,n);var t=(0,w.default)(this,(n.__proto__||(0,h.default)(n)).call(this,e));return t.handleMouseUp=function(){_=!1},t.handleMouseMove=function(e){if(_){var n=e.clientX,t=document.querySelector("#layoutLeft"),a=t.getBoundingClientRect().left,o=parseInt(n-a)+3;o<200||(t.style.width=o+"px")}},t.handleScroll=function(e){var n=t.refs.ncWorkbenchPageScroll.getBoundingClientRect(),a=n.y;n.width;if(a==t.initScrollContainerTop&&t.setState({suck:!1}),a<t.initScrollContainerTop){if(t.state.suck)return;t.containerWidthChange(),t.setState({suck:!0})}},t.containerWidthChange=function(){var e=t.refs.ncWorkbenchPageOwnpage.getBoundingClientRect().width;document.getElementsByClassName("nc-workbench-page-header")[0].style.width=e+"px",document.getElementById("suckTableHeader")&&(document.getElementById("suckTableHeader").style.width=e+"px")},t.state={suck:!1},t.initScrollContainerTop,t}return(0,b.default)(n,e),(0,g.default)(n,[{key:"componentDidMount",value:function(){window.addEventListener("resize",this.containerWidthChange);var e=this.refs.ncWorkbenchPageScroll.getBoundingClientRect().y;this.initScrollContainerTop=e}},{key:"componentWillUnmount",value:function(){window.removeEventListener("resize",this.containerWidthChange)}},{key:"render",value:function(){var e=this.state.suck;return m.default.createElement("div",{className:"nc-workbench-page-scroll",onScroll:this.handleScroll},m.default.createElement("div",{className:" nc-workbench-page "},m.default.createElement("div",{className:"nc-workbench-page-container "+(e?"nc-workbench-suck":""),ref:"ncWorkbenchPageScroll"},this.props.header?this.props.header:null,m.default.createElement("div",{onMouseMove:this.handleMouseMove,onMouseUp:this.handleMouseUp,ref:"ncWorkbenchPageOwnpage",className:"nc-workbench-ownpage "+(2===this.props.children.length?"nc-workbench-ownpage-all":"")+" "+this.props.className},this.props.children))))}}]),n}(k.Component),r.defaultProps={className:""},c),C=(l=i=function(e){function n(e){(0,f.default)(this,n);var t=(0,w.default)(this,(n.__proto__||(0,h.default)(n)).call(this,e));return t.handleMouseDown=function(){_=!0},t}return(0,b.default)(n,e),(0,g.default)(n,[{key:"render",value:function(){return m.default.createElement("div",{id:"layoutLeft",className:"nc-workbench-ownpage-left "+this.props.className},m.default.createElement("span",{className:"layout-drag-block",onMouseDown:this.handleMouseDown}),this.props.children)}}]),n}(k.Component),i.defaultProps={className:""},l),P=(d=s=function(e){function n(e){return(0,f.default)(this,n),(0,w.default)(this,(n.__proto__||(0,h.default)(n)).call(this,e))}return(0,b.default)(n,e),(0,g.default)(n,[{key:"render",value:function(){return m.default.createElement(v,{areaCode:v.config.HEADER,className:"nc-workbench-page-header "+this.props.className},this.props.children)}}]),n}(k.Component),s.defaultProps={className:""},d),L=(u=p=function(e){function n(){return(0,f.default)(this,n),(0,w.default)(this,(n.__proto__||(0,h.default)(n)).apply(this,arguments))}return(0,b.default)(n,e),(0,g.default)(n,[{key:"render",value:function(){return m.default.createElement("div",{className:"nc-workbench-ownpage-right "+this.props.className},this.props.children)}}]),n}(k.Component),p.defaultProps={className:""},u);M.propTypes={children:x.default.any.isRequired},C.propTypes={children:x.default.any.isRequired},L.propTypes={children:x.default.any.isRequired},n.PageLayout=M,n.PageScrollLayout=N,n.PageLayoutHeader=P,n.PageLayoutLeft=C,n.PageLayoutRight=L},896:function(e,n,t){var a=t(897);"string"==typeof a&&(a=[[e.i,a,""]]);var o={hmr:!0,transform:void 0,insertInto:void 0};t(15)(a,o);a.locals&&(e.exports=a.locals)},897:function(e,n,t){(e.exports=t(13)(!1)).push([e.i,'.nc-workbench-page{position:absolute;top:0;z-index:10;height:100%;width:100%;padding:8px 0 0;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column}.nc-workbench-page .nc-workbench-page-header{-webkit-box-flex:0;flex:0 0 45px;display:-webkit-box;display:flex;-webkit-box-pack:justify;justify-content:space-between;-webkit-box-align:center;align-items:center;border-bottom:1px solid #d9d9d9;border-radius:3px 3px 0 0;background:#fff;padding:0 20px}.nc-workbench-page .nc-workbench-page-header .pageLayoutHeaderOwn{font-size:16px;color:#111;font-weight:700}.nc-workbench-page .nc-workbench-page-header .pageLayoutHeaderOwn .iconfont{margin-right:6px;border-radius:50%;font-size:16px;padding:4px;font-weight:400;cursor:pointer}.nc-workbench-page .nc-workbench-page-header .pageLayoutHeaderOwn .iconfont:hover{background:#e8e8e8}.nc-workbench-page .nc-workbench-page-header .pageLayoutHeaderOwn span[type=ncdiv]{font-size:inherit}.nc-workbench-page .nc-workbench-page-header .header_in_icon{display:-webkit-box;display:flex;font-weight:700;font-size:16px;color:#111;line-height:24px}.nc-workbench-page .nc-workbench-page-header .header_in_icon span[type=ncdiv]{font-size:inherit}.nc-workbench-page .nc-workbench-ownpage{min-width:800px;border-radius:0 0 3px 3px}.nc-workbench-page .nc-workbench-ownpage-all{height:100%;display:-webkit-box;display:flex;-webkit-box-pack:start;justify-content:flex-start;background:#fff;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}.nc-workbench-page .nc-workbench-ownpage-left{position:relative;height:100%;min-width:280px}.nc-workbench-page .nc-workbench-ownpage-left .layout-drag-block{position:absolute;right:-3px;z-index:10;top:50%;-webkit-transform:translateY(-50%);transform:translateY(-50%);display:block;padding:0 3px;height:100%;border-radius:3px;cursor:w-resize}.nc-workbench-page .nc-workbench-ownpage-left .layout-drag-block:after{content:"";display:block;width:1px;height:100%;background:#d9d9d9}.nc-workbench-page .nc-workbench-ownpage-left .layout-drag-block:hover:after{width:2px;background:rgba(0,0,0,.54)}.nc-workbench-page .nc-workbench-ownpage-right{min-width:50%;-webkit-box-flex:1;flex:1}.nc-workbench-page-scroll{height:100%;width:100%;overflow-y:auto}.nc-workbench-page-scroll .nc-workbench-page-container{margin-top:0;min-height:100%;background:#fff;border-radius:3px}.nc-workbench-page-scroll .nc-workbench-page-container .nc-workbench-page-header{position:static;-webkit-transition:top .7s ease;transition:top .7s ease;height:46px}.nc-workbench-page-scroll .nc-workbench-page-container .suck-table-header{position:static;-webkit-transition:top .7s ease;transition:top .7s ease}.nc-workbench-page-scroll .nc-workbench-suck{padding-top:80px}.nc-workbench-page-scroll .nc-workbench-suck .nc-workbench-page-header{position:fixed;z-index:1;top:44px}.nc-workbench-page-scroll .nc-workbench-suck .suck-table-header{position:fixed;z-index:1;top:94px}@media screen and (min-width:1280px){.nc-workbench-page{padding:8px 16px 0}}@media screen and (min-width:1440px){.nc-workbench-page{padding:8px 16px 0}}@media screen and (min-width:1600px){.nc-workbench-page{padding:8px 16px 0}}',""])}}]);