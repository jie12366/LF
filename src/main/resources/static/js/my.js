//全选
function ckAll(){
    var flag=document.getElementById("allChecks").checked;
    var cks=document.getElementsByName("check");
    for(var i = 0;i<cks.length;i++){
        cks[i].checked=flag;
    }
}

//批量删除
function DelSelect() {
    var Checkbox = false;
    $("input[name='check']").each(function() {
        if (this.checked == true) {
            Checkbox = true;
        }
    });
    if (Checkbox) {
        var t = confirm("您确认要删除选中的内容吗？");
        if (t == false) {
            return false;
        } else {
            var checkedID="";
            $("input[name='check']").each(function() {
                if (this.checked == true) {
                    checkedID += this.value + ",";

                }
            });
            checkedID = checkedID.substring(0,checkedID.length-1);
            window.location.replace("/deleteContent?id="+checkedID);
        }
    } else {
        alert("请选择您要删除的内容!");
        return false;
    }
}