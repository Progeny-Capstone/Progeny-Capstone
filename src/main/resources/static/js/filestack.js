
// -------------- PICKER --------------
window.addEventListener('DOMContentLoaded', function () {
    const apikey = fileStackKey;
    const client = filestack.init(apikey);
    const options = {
        maxFiles: 20,
        uploadInBackground: false,
        onOpen: () => console.log('opened!'),
        onUploadDone: (res) => console.log(res),
    };
    client.picker(options).open();
});



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
        onUploadDone: (res) => console.log(res),
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