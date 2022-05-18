# T120B169-Fundamentals-of-App-Development
Appsas moduliui "Mobilių programėlių kūrimo pagrindai". Tema: Semestro darbų planuotojas.

# color recourses
res >> values >> colors.xml
# string values
res >> values >> strings.xml

# Database
event table >> EventTable.java
int id; autogenerated
String courseName; 
String examName;
String examDesc; *description nullable
String examDate;
String firstRetakedate; nullable
String examTime;
String firstRetakeTime; nullable
String sources; nullable

SQL >> EventDAO.java

# Date and time
Date format yyyy-MM-dd (example: 2022-04-09)
Time format HH:mm (example: 09:15)

# Fragments
Fragmentai turi būti aprašyti: res >> navigation >> mobile.navigation.xml
Kiekvienas fragmentas turi atskirą layout (res >> layout) ir kodą (java >> com.example.studin)

Kodas skirstomas aplankais: database, ui ir pan.
Fragmentuose kintamieji iš layout priskiriami per binding.

Eiti į child fragmentą: 
Navigation.findNavController(view).navigate(R.id.nav_editEvent); R.id.nav_editEvent - fragmento vardas iš mobile.navigation.xml
Grįžti į tėvinį fragmentą: 
Navigation.findNavController(view).popBackStack();
