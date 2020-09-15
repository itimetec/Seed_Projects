import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './_helpers/auth.guard';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: "",
    redirectTo: "/login",
    pathMatch: "full"
  },
  {
    path: 'login',
    component: LoginComponent
  },

  {
    path: 'signup',
    component: SignupComponent
  },

  {
    path:'home',
    component :HomeComponent,
    canActivate: [AuthGuard]
  },

  {
    path:'dashboard',
    component :DashboardComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "**",
    redirectTo: "/login",
    pathMatch: "full"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
