import {preventDefaultDragoverAndDrop} from "./service";
import {AudioRecordTapesContainer, AudioRecordTapesPlayer, Component} from "./view";

preventDefaultDragoverAndDrop();

const uploadZone = document.getElementById('upload-zone');
const recordPlayer = document.getElementById('record-player');

const audioRecordTapesPlayer = new AudioRecordTapesPlayer();
const audioRecordTapesContainer = new AudioRecordTapesContainer();
const image = new Component({
                              elementName: 'image',
                              eventListeners: {
                                click: (ev) => console.dir(ev)
                              },
                              attributes: {
                                src: 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Sunset%2C_Porirua_harbour_entrance.jpg/1920px-Sunset%2C_Porirua_harbour_entrance.jpg'
                              }
                            });

image.appendToChildren(audioRecordTapesContainer.element);

audioRecordTapesPlayer.audioRecordTapesContainer = audioRecordTapesContainer;
audioRecordTapesContainer.renderAt(uploadZone);
audioRecordTapesPlayer.renderAt(recordPlayer);
