(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-mine-depart"],{"6e8d":function(t,n,e){"use strict";var i=function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("v-uni-view",{staticStyle:{"overflow-y":"scroll",height:"100%"}},t._l(t.members,function(n,i){return e("uni-list",{key:i},[e("uni-list-item",{attrs:{note:n.name,"show-arrow":!0},on:{click:function(e){e=t.$handleEvent(e),t.toDetail(n.uuid)}}})],1)}),1)},u=[];e.d(n,"a",function(){return i}),e.d(n,"b",function(){return u})},"979e":function(t,n,e){"use strict";e.r(n);var i=e("6e8d"),u=e("bbf6");for(var a in u)"default"!==a&&function(t){e.d(n,t,function(){return u[t]})}(a);var r=e("2877"),o=Object(r["a"])(u["default"],i["a"],i["b"],!1,null,"83477bca",null);n["default"]=o.exports},bbf6:function(t,n,e){"use strict";e.r(n);var i=e("c85c"),u=e.n(i);for(var a in i)"default"!==a&&function(t){e.d(n,t,function(){return i[t]})}(a);n["default"]=u.a},c85c:function(t,n,e){"use strict";var i=e("288e");Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var u=i(e("2cd1")),a=i(e("319c")),r={components:{uniList:u.default,uniListItem:a.default},data:function(){return{uid:"",members:null}},onLoad:function(){var t=uni.getStorageSync("uid");this.uid=t;var n=this;this.$api.get("/depart/".concat(t),{},function(t){n.members=t.data})},methods:{toDetail:function(t){uni.navigateTo({url:"/pages/mine/information/information?uid="+t})}}};n.default=r}}]);