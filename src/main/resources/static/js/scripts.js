/*!
    * Start Bootstrap - SB Admin v7.0.5 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2022 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});
function ajaxRequest(url, type, data) {
    $.ajax({
        url: url,
        type: type,  //默认值: "GET"请求方式 ("POST" 或 "GET")
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json;charset=UTF-8",
        success: function(data){      // 回调结果，如果成功
            toast_msg.textContent=data['message'];
            toast.show();
            if (data['status'] == 1){
                location.reload();
            };
        },
        error: function() {
            alert("发生错误，请反馈！");
        }
    })
}