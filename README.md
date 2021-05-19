# FirstTask

### Android application made with Android Studio & Java as per requirement for first task by Physicswallah

* Consists of single activity - MainActivity.
* MainActivity consists of two recyclerviews - one for displaying the **Facilities** and other for displaying **Exclusions**.
* Each recyclerview consists of **Cardview** to acheive better UI.

* Facilities:
  * Shows the list of facilities by name.
  * On clicking a facility:
  * A dialog box opens with a recycler view displaying all the options available in facility.
  * Here, each option is shown along with the icon.
  * Assuming icon to be a string as from JSON Api, its value is shown in textview.
  * User can select any one of the options.
  
* After selection, facility name will be updated to "Facility - option".
* On any selection made, a validation is performed to check if it violates any exclusion.
* If the selection was made against exclusion, a Toast is shown with message "Invalid Selection*.

* Exclusions:
  * Shows the list of exclusions.
  * Each exclusion item in list consist of two Facility-Option pair.
  
* Third party libraries used:
  * OkHttp - to make http request to the api.
  * Gson - to parse the json object into java object.
  
* Screenshots:
#### Layout
<img src = "https://github.com/yashjohri/Screenshots/blob/main/FirstTask/img_1_layout.png" width="500">

#### Option Selection for Facility
<img src = "https://github.com/yashjohri/Screenshots/blob/main/FirstTask/img_2_options.png" width="500">

#### Invalid Selection
<img src = "https://github.com/yashjohri/Screenshots/blob/main/FirstTask/img_3_invalid_selection.png" width="500">
