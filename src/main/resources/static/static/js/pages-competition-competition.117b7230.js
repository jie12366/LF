(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-competition-competition"],{"08b4":function(t,i,e){"use strict";var n=e("6fd0"),a=e.n(n);a.a},2693:function(t,i,e){"use strict";var n=function(){var t=this,i=t.$createElement,e=t._self._c||i;return e("v-uni-view",{staticStyle:{"overflow-y":"scroll",height:"100%"}},t._l(t.activityList,function(i,n){return e("v-uni-view",{key:n,staticClass:"item",on:{click:function(e){e=t.$handleEvent(e),t.toItem(i.id)}}},[e("v-uni-image",{staticClass:"img",attrs:{src:i.picture,mode:"scaleToFill"}}),e("v-uni-view",{staticClass:"date"},[t._v(t._s(i.date))]),e("v-uni-view",{staticClass:"title"},[t._v(t._s(i.title))]),e("v-uni-view",{staticClass:"content"},[t._v(t._s(i.content))])],1)}),1)},a=[];e.d(i,"a",function(){return n}),e.d(i,"b",function(){return a})},5021:function(t,i,e){"use strict";Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0;var n={data:function(){return{activityList:[]}},onShow:function(){var t=this;this.$api.get("/activity",{},function(i){t.activityList=i.data;for(var e=0;e<t.activityList.length;e++)t.activityList[e].content.length>35&&(t.activityList[e].content=t.activityList[e].content.substring(0,35)+"...")})},methods:{toItem:function(t){uni.navigateTo({url:"/pages/competition/detail?id="+t})}}};i.default=n},"6fd0":function(t,i,e){var n=e("922f");"string"===typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);var a=e("4f06").default;a("06cc6477",n,!0,{sourceMap:!1,shadowMode:!1})},"922f":function(t,i,e){i=t.exports=e("2350")(!1),i.push([t.i,".item[data-v-489ebc02]{font-size:%?30?%;width:90%;height:%?240?%;margin:auto;margin-top:%?20?%;padding-bottom:%?20?%}.img[data-v-489ebc02]{width:%?300?%;height:%?200?%;display:inline-block}.title[data-v-489ebc02]{position:relative;width:%?400?%;top:%?-240?%;left:%?300?%;text-align:center;font-family:blod}.content[data-v-489ebc02]{position:relative;width:%?380?%;color:#666;font-size:%?28?%;top:%?-220?%;left:%?310?%}.date[data-v-489ebc02]{color:#666;font-size:%?28?%;border-bottom:#999 %?1?% solid}",""])},de45:function(t,i,e){"use strict";e.r(i);var n=e("5021"),a=e.n(n);for(var o in n)"default"!==o&&function(t){e.d(i,t,function(){return n[t]})}(o);i["default"]=a.a},e4c9:function(t,i,e){"use strict";e.r(i);var n=e("2693"),a=e("de45");for(var o in a)"default"!==o&&function(t){e.d(i,t,function(){return a[t]})}(o);e("08b4");var c=e("2877"),s=Object(c["a"])(a["default"],n["a"],n["b"],!1,null,"489ebc02",null);i["default"]=s.exports}}]);