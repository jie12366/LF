function previousPage(){
    var pageNum = $('#pageNum').val()
    if (pageNum <= 1){
        $('.previous').addClass('disabled')
        alert("没有更前的页")
    }else {
        $('.previous').removeClass('disabled')
        var previous = pageNum - 1
        window.location.href = "/admin/announce?start=" + previous
    }
}

function nextPage(){
    var pageNum = $('#pageNum').val()
    var pageSize = $('#pages').val()
    if (pageNum >= pageSize){
        $('.nextPage').addClass('disabled')
        alert("没有更多的页")
    }else {
        var next = parseInt(pageNum) + 1
        $('.previous').removeClass('disabled')
        $('.nextPage').removeClass('disabled')
        window.location.href = "/admin/announce?start=" + next
    }
}