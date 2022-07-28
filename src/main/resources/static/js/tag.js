var toastLive = document.getElementById('liveToast');
var toast_msg = document.getElementById('toast_msg');
var toast = new bootstrap.Toast(toastLive);

var exampleModal = document.getElementById('removeModal')
exampleModal.addEventListener('show.bs.modal', function (event) {
    // Button that triggered the modal
    var button = event.relatedTarget
    // Extract info from data-bs-* attributes
    var tag_id = button.getAttribute('data-bs-whatever')
    var remove_button = exampleModal.querySelector('.modal-footer #remove_link')
    remove_button.href = '/tag/delete/' + tag_id
});
var exampleModal = document.getElementById('changeModal');
var cur_tag;
exampleModal.addEventListener('show.bs.modal', function (event) {
    // Button that triggered the modal
    var button = event.relatedTarget
    // Extract info from data-bs-* attributes
    var tag_name = button.getAttribute('data-bs-name')
    var old_name = exampleModal.querySelector('.modal-body #old-tag-name')
    old_name.placeholder = tag_name

    cur_tag = {
        "tag_id": button.getAttribute('data-bs-id'),
        "name": tag_name,
        "count": button.getAttribute('data-bs-count'),
        "user": button.getAttribute('data-bs-user')
    };

});
function insertTag() {
    var tagModal = document.getElementById('tag_name');
    var tag_name = tagModal.value.replace(/^\s*|\s*$/g,"");
    if (tag_name == undefined || tag_name == null || tag_name == ''){
        toast_msg.textContent='请为标签起个名字！';
        toast.show();
        return;
    };
    var data = {
        "name": tag_name
    };
    ajaxRequest("/tag/insert", "POST", data);
};
function updateTag() {
    var new_name = document.getElementById('new-tag-name').value;
    if(new_name == undefined || new_name == null || new_name == ''){
        toast_msg.textContent='请为标签起个名字！';
        toast.show();
        return;
    }
    if(new_name.toLowerCase() == cur_tag.name.toLowerCase()){
        toast_msg.textContent='名字不能与原名相同！';
        toast.show();
        return;
    }
    console.log(new_name)
    cur_tag.name = new_name;
    ajaxRequest("/tag/update_name", "POST", cur_tag);
};
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