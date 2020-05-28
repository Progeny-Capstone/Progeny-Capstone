// 'use strict';

//-------------- GET USER MIC CONTROL --------------
navigator.mediaDevices.getUserMedia({audio:true})
    .then(stream => {handlerFunction(stream)})

//-------------- WHAT DO WE DO WITH THE AUDIO --------------
function handlerFunction(stream) {
    rec = new MediaRecorder(stream); // Creates a new MediaRecorder object, given a MediaStream to record.
    rec.ondataavailable = e => {
        audioChunks.push(e.data);
        console.log(audioChunks);
        if (rec.state == "inactive"){
            let blob = new Blob(audioChunks,{type:'audio/mpeg-3'}); //The Blob() constructor can create blobs from other objects.
            recordedAudio.src = URL.createObjectURL(blob);
            recordedAudio.controls=true;
            recordedAudio.autoplay=true;
            sendData(blob)
        }
    }
}
function sendData(data) {}

//-------------- (START) RECORD USER AUDIO --------------
record.onclick = e => {
    console.log('I was clicked')
    record.disabled = true;
    record.style.backgroundColor = "blue"
    stopRecord.disabled=false;
    audioChunks = []; // The bucket used in the handlerFunction(stream)
    rec.start();
}

//-------------- (STOP) RECORD USER AUDIO --------------
stopRecord.onclick = e => {
    console.log("I was clicked")
    record.disabled = false;
    stop.disabled=true;
    record.style.backgroundColor = "red"
    rec.stop();
}