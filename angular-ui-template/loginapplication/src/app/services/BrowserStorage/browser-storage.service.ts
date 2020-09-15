import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BrowserStorageService {

  constructor() { }

  setLocalStorage(key, value) {
    localStorage.setItem(key, value);
  }

  getLocalStorageItem(key) : string {
    return localStorage.getItem(key);
  }

  removeLocalStorageItem(key) {
    localStorage.removeItem(key)
  }

  setSessionStorage(key, value) {
    sessionStorage.setItem(key, value);
  }

  getSessionStorage(key) : string {
    return sessionStorage.getItem(key);
  }

  removeSessionStorage(key) {
    sessionStorage.removeItem(key)
  }
}
