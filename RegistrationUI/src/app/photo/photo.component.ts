import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Personal } from '../models/personal';
import { UploadFileService } from '../service/upload-file.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommonService } from '../shared/common.service';
import { LocalService } from '../Encrypt/local.service';

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  styleUrls: ['./photo.component.css']
})
export class PhotoComponent implements OnInit {
  user:Personal;
  //recieved_data:string;
  photoUpload : FormGroup;
  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  fileInfos: Observable<any>;
  url:any;

  constructor(private commonData:CommonService,private localService:LocalService,private router:Router,private readonly fb: FormBuilder,private uploadService: UploadFileService) { 
    //recieving value from login navigation
   // const navigation = this.router.getCurrentNavigation()!;
    //const state = navigation.extras.state as {recieved_data: any};
    //this.user = state.recieved_data;
  
    this.photoUpload = this.fb.group({
      first_name: [''],
      mobileNo:[''],
      gender:[''],
      email_id:[''],
    });
  }

  selectFile(event:any) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress = 0;
  
    this.currentFile = this.selectedFiles.item(0)!;
    this.uploadService.uploadPhoto(this.currentFile).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total!);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          this.fileInfos = this.uploadService.getPhoto();
          //this.fileInfos = this.uploadService.getFiles(this.currentFile);
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        //this.currentFile = undefined;
      });
  
    //this.selectedFiles = undefined;
  }

  ngOnInit() {
    this.user = this.commonData.getLoggedInUser();
    if(this.user == null){
      this.user = this.localService.getJsonValue('loggedInUser');
    }
    this.fileInfos = this.uploadService.getPhoto();
    console.log(this.fileInfos);
  }

  updateCandidateStage(){
      this.router.navigate(['sign']);
  }

  onSubmit(){
    
  }

  get f(){
    return this.photoUpload.controls;
  }

}
