import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-recipe-place-search',
  templateUrl: './recipe-place-search.component.html',
  styleUrls: ['./recipe-place-search.component.css']
})
export class RecipePlaceSearchComponent implements OnInit {

  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];
  noRecords: boolean = false;
  currentLat: any;
  currentLong: any;
  geolocationPosition: any;

  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (this.recipeValue !== null) {
      this._http.get('https://api.edamam.com/search?q=' + this.recipeValue +
        '&app_id=fe0c0f02&app_key=428abaa09890f4ea5d2798cefb72921f	&from=0&to=10&calories=591-722&health=alcohol-free').subscribe((recipes: any) => {
          this.noRecords = recipes.count == 0 ? true:false;
          console.log(this.noRecords)
          this.recipeList = Object.keys(recipes.hits).map((rec,index) =>  {
            const recipe = recipes.hits[index].recipe;
            return { name: recipe.label, content: recipe.digest[0].schemaOrgTag, icon: recipe.image, add: recipe.address, url: recipe.url }
          });
        },error => { 
          this.noRecords = true;
        }
        );

    }

    if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') {
      this._http.get('https://api.foursquare.com/v2/venues/search?client_id=GSJJ3PYHRUKB2CM5XECAU35L3EYNHKCOHXRKJ5YYUHVX1C0Q' +
        '&client_secret=15FVGHRXN01J1UEJBERTWAXPKPAUCQ2TCP2Y4G5AHGIPAION&v=20180323&limit=10&near=' + this.placeValue + '&query=' + this.recipeValue).subscribe((restaurants: any) => {
          this.venueList = Object.keys(restaurants.response.venues).map((input,index) => {
            const restaurant = restaurants.response.venues[index];
            console.log(restaurant)
            return { name: restaurant.name, location: restaurant.location };

          })
        });
       
    }
  }

}
