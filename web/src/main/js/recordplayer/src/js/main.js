import {preventDefaultDragoverAndDrop} from "./service";
import {AudioRecordTapesContainer, AudioRecordTapesPlayer} from "./view";

preventDefaultDragoverAndDrop();

const uploadZone = document.getElementById('upload-zone');
const recordPlayer = document.getElementById('record-player');

const audioRecordTapesPlayer = new AudioRecordTapesPlayer();
const audioRecordTapesContainer = new AudioRecordTapesContainer();

audioRecordTapesPlayer.audioRecordTapesContainer = audioRecordTapesContainer;
audioRecordTapesContainer.renderAt(uploadZone);
audioRecordTapesPlayer.renderAt(recordPlayer);
