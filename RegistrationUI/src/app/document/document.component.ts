import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UploadFileService } from '../service/upload-file.service';

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.css']
})
export class DocumentComponent implements OnInit {

  formData: FormGroup;
  fileToUpload1: File;
  fileToUpload2: File;
  
  fileToUpload3: File;
  fileToUpload4: File;
  message = '';
  
  constructor(private router: Router, private formBuilder: FormBuilder,private uploadService:UploadFileService) { }

  ngOnInit(): void {
    this.formData = this.formBuilder.group({
      files   : []
    });
  }

  handleFileInput1(event) {
    this.fileToUpload1 = <File>event.target.files[0];
  }
 
  handleFileInput2(event) {
    this.fileToUpload2 = <File>event.target.files[0];
  }

  
  handleFileInput3(event) {
    this.fileToUpload3 = <File>event.target.files[0];
  }
 
  handleFileInput4(event) {
    this.fileToUpload4 = <File>event.target.files[0];
  }

  onSubmit(){
    const formData: FormData = new FormData();
    formData.append('document', this.fileToUpload1, 'SSC_'+this.fileToUpload1.name);
    formData.append('document', this.fileToUpload2, 'HSC_'+this.fileToUpload2.name); 
    formData.append('document', this.fileToUpload3, 'GRAD_'+this.fileToUpload3.name);
    formData.append('document', this.fileToUpload4, 'CAT_'+this.fileToUpload4.name); 
    this.uploadService.uploadDocuments(formData).subscribe(
      resp => {
        this.message = JSON.stringify(resp.body);
      },
      err => {
        console.log(err);
      });

  }
  updateCandidateStage(){
    this.router.navigate(['payment']);
}

}
