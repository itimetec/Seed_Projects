<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use GuzzleHttp\Exception\GuzzleException;
use GuzzleHttp\Client;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $apiResponce = $this->getGitHubUser();

        if(!$apiResponce) {

            $apiResponce = 'Error!! Issue in calling the 3rd party';
        }

        return view('home',
            ['api' => $apiResponce]
        );
    }

    public function getGitHubUser() {

        $client = new \GuzzleHttp\Client();
        $res = $client->request('GET', 'https://api.github.com/repos/guzzle/guzzle');
        
        if($res->getStatusCode() == 200) {
            return $res->getBody();
        } else {
            return null;
        }
    }
}
