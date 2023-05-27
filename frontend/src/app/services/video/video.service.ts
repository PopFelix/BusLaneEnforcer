import { HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { UnauthorizedLog } from 'src/app/components/unauthorized-vehicles/unauthorized-log';
import { ResponseFile } from 'src/app/components/upload/response-file';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VideoService {
  videoUrl = environment.baseUrl+'/video/';
  scanUrl = environment.pythonUrl+'/scan';
  currentUpload!: File;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(protected http: HttpClient) { }

  getVideo(name: string){
    this.http.get<any>(this.videoUrl+name);
  }

  scanVideo(file: File): Observable<Object>{
    console.log(this.scanUrl);
    var form = new FormData();
    form.append('upload_file',file);
    return this.http.post<any>(this.scanUrl,form);
  }

}
