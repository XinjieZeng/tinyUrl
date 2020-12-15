function sendUrl(url) {
    $.ajax({
        url: 'http://localhost:8080/toshort/' + url,
        type: 'GET',
        contentType: false,
        processData: false,
        accept : "*/*",

        success : function (data) {
            createDom(url.length, data);
        },

        error: function(data) {
            alert("fail" + data);
        }

    });
}

function createDom(originLength, data) {
    var parent = document.getElementById('parent');
    var header = document.createElement('h3');
    header.innerHTML="TinyURL was created";
    parent.appendChild(header);

    var content = document.createElement('p');
    content.innerHTML='The following URL: \n' + data;
    parent.appendChild(content);

    var description = document.createElement('p');
    description.innerHTML = 'long url has a length of ' + originLength + " characters and resulted in the following TinyURL which has a length of " + data.length + " characters";
    parent.appendChild(description);
}
