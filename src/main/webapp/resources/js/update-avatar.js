function updateAvatar() {
    $('#file-input').trigger('click');
}

function uploadSelectedImage() {
    let fileInput =  $("#file-input");
    let image = fileInput[0].files[0]
    if(image == null) { return }
    let data = new FormData()
    data.append("file", image)
    $.ajax("/update-avatar", {
        method: "POST",
        type: "POST",
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            let fileInfo = JSON.parse(data)
            jQuery("#image").attr("src", "/files/" + fileInfo.fileId)
        }
    })
}