import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
export class ApiLists{

    public LOGIN:string ="/api/login"; 
    public REGISTER:string ="/api/register";
    public GEN_OTP:string="/api/genOtp";
    public INSERT_PERSONAL:string="/api/savePersonal";
    public GET_PERSONAL:string="/api/getPersonal";
    public UPLOAD_FILE:string="/ipa/upload";
    public GET_FILE:string="/ipa/files";
    public GET_MENU_STAGE:string="/api/menuStage";
    public LOAD_REGISTRATION:string="/api/load_register";
    public GET_PHOTO = "/ipa/files/";
    public GET_DOCUMENTS = "/upi/upload/documents";
    public GEN_PAYMENT_REQ = "/pay/generatePayUrl";
}