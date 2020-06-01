

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




function updatePhotoForm(result) {
    const fileData = result.filesUploaded[0];
    photoInput.value = fileData.url; //this is the good part
    console.log(fileData.url);
    console.log(photoInput.value);
};



// -------------- FILESTACK IMG UPLOAD --------------
// const client = filestack.init(fileStackKey);
//
// let options = {
//     "displayMode": "inline",
//     "container": ".picker-content",
//     "maxFiles": 4,
//     "accept": [
//         ""
//     ],
//     "storeTo": {
//         "container": "devportal-customers-assets",
//         "path": "user-uploads/",
//         "region": "us-east-1"
//     },
//     "fromSources": [
//         "url"
//     ],
//     "uploadInBackground": false
// };
//
// picker = this.client.picker(options);
// picker.open();