

// -------------- FILESTACK --------------
window.addEventListener('DOMContentLoaded', function () {

    // -------------- API KEY (Keys.js) --------------
    const apikey = fileStackKey;
    const client = filestack.init(apikey);

    // -------------- UPLOAD PREFERENCES --------------
    const options = {
        displayMode: 'inline',
        container: '#inline',
        maxFiles: 20,
        uploadInBackground: false,
        onUploadDone: function (res){
            console.log(res);

            console.log(res.filesUploaded[0].url);

            let filestackUrl = `<input type="hidden" name="filestackUrl" value="${res.filesUploaded[0].url}"/>`;

            $("#recording-form").append(filestackUrl);


        },



    };




    // -------------- FILE PICKER --------------
    const picker = client.picker(options);

    // -------------- HTML ELEMENTS --------------
    const openBtn = document.getElementById('open');
    const closeBtn = document.getElementById('close');

    // -------------- EVENT LISTENERS --------------
    openBtn.addEventListener('click', () => picker.open());
    closeBtn.addEventListener('click', () => picker.close());

});