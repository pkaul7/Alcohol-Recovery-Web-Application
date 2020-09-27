

/**
 Gets Content from Backend, extracts and return the content

 */


/**
 Loads content from a backend page
 Put the content of the page into the frontend page.
 Arguments:
 url of the other backend HTML page to load

 */

function loadHTML(url, fun, storage, param)
{
    var xhr = createXHR();
    xhr.onreadystatechange=function()
    {
        if(xhr.readyState == 4)
        {
            //if(xhr.status == 200)
            {
                storage.innerHTML = xhr.responseText;
                fun(storage, param);
            }
        }
    };

    xhr.open("GET", url , true);
    xhr.send(null);

}

function loadLogIn(url)
{
    var xhr = createXHR();
    xhr.onreadystatechange=function()
    {
        if(xhr.readyState == 4)
        {
            if(xhr.status == 200)
            {

                if (xhr.responseText === "false") {
                    //window.alert("Sorry, you need to be logged in to do that.");
                    //window.location.href="login.html";}
                }
            }
        }
    };

    xhr.open("GET", url , true);
    xhr.send(null);

}



/**
 need a new load method for each backend access call
 need a storage and display div
 */


function processHTML(temp, target)
{
    target.innerHTML = temp.innerHTML;
}

function loadHl(url)
{
    var y = document.getElementById("storage");
    var x = document.getElementById("displayed");
    loadHTML(url, processHTML, x, y);
}

function loadDate(url)
{
    var y = document.getElementById("dateStorage");
    var x = document.getElementById("date");
    loadHTML(url, processHTML, x, y);
}

function loadUsername(url)
{
    var y = document.getElementById("uNameStorage");
    var x = document.getElementById("uName");
    loadHTML(url, processHTML, x, y);
}

function loadName(url) {
    var y = document.getElementById("nameStorage");
    var x = document.getElementById("header-name");
    loadHTML(url, processHTML, x, y);
}

function loadHeight(url) {
    var y = document.getElementById("heightStorage");
    var x = document.getElementById("header-height");
    loadHTML(url, processHTML, x, y);
}

function loadWeight(url) {
    var y = document.getElementById("weightStorage");var x = document.getElementById("header-weight");
    loadHTML(url, processHTML, x, y);
}

function loadDate() {
    var date = new Date();
    var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    var fulldate = months[date.getMonth()] + " " + date.getDate() + ", " + date.getFullYear();
    document.getElementById("header-date").innerHTML = fulldate;
}

function loadMotivationalFact() {

        var index = Math.round(Math.random() * 15);
        var quote = actual_JSON[index]["quote"];
        var author = actual_JSON[index]["author"];
        document.getElementById("daily-tip").innerHTML = "<p>" + quote + " - <i>"+author+"</i></p>";

}

function loadHealthFact() {
    //check condition first.
    var condition = "41309000";
 
    var xhr = createXHR();
    xhr.onreadystatechange=function()
    {
        if(xhr.readyState == 4)
        {
            
            condition = xhr.responseText;
         
             var fact = "";
             var index = Math.round(Math.random() * 2);

             if (condition == "41309000") {
                 fact = liver_JSON["facts"][index];
             }
             else if (condition == "84757009") {
                 fact = epilepsy_JSON["facts"][index];
             }
             else if (condition == "19303008") {
                 fact = myopathy_JSON["facts"][index];
             }

             $("#patient-fact").html("<p>"+fact+"</p>");
         
            
        }
    };

    xhr.open("GET", "https://apps.hdap.gatech.edu/alcoholrecovery2-backend/user/getCondition", true);
    xhr.send(null);
    
   

}


function getMotivationalFacts(callback) {
    var xobj = new XMLHttpRequest();
    xobj.overrideMimeType("application/json");
    xobj.open('GET', 'motivational-facts.json', true); // Replace 'my_data' with the path to your file
    xobj.onreadystatechange = function () {
        if (xobj.readyState == 4 && xobj.status == "200") {
            // Required use of an anonymous callback as .open will NOT return a value but simply returns undefined in asynchronous mode
            callback(xobj.responseText);
        }
    };
    xobj.send(null);
}

function isLoggedIn(){
    if(window.location.href.indexOf("login.html")!=-1){
        return;
    }

    $(document.body).hide();
    var url =  API_ROOT + "/alcoholrecovery2-backend/login/loggedIn";
    $.get(url,function(data){
        if(data==-1){
            location.href="login.html"
        }else{
            USER_ID = data;
            $(document.body).show();
        }
    }).fail(function(data){
        location.href="login.html";
    })
}

var API_ROOT = ""
if(window.location.href.indexOf("apps.hdap")!=-1){
     API_ROOT = "https://apps.hdap.gatech.edu"
}else {
     API_ROOT = "http://localhost:8080"

}
isLoggedIn();

/*function logout(){
    var url =  API_ROOT + "/alcoholrecovery2-backend/login/logout";
    $.get(url,function(data){
        location.href="login.html"
    })
}


 $(document).ready(function () {

     $("a.logout").each(function(el){
         $(this).attr("href","");
         $(this).click(logout)
     })

});*/

var USER_ID = -1;
var actual_JSON = {"0":{"quote":"If there is no struggle, there is no progress","author":"Frederick Douglass"},"1":{"quote":"Without continual growth and progress, such words as improvement, achievement, and success have no meaning","author":"Benjamin Franklin"},"2":{"quote":"It's not about how hard you hit. It's about how hard you can get hit and keep moving forward. How much you can take and keep moving forward","author":"Rocky Balboa"},"3":{"quote":"One of the hardest things was learning that I was worth recovery","author":"Demi Lovato (Former Addict)"},"4":{"quote":"Strength does not come from physical capacity. It comes from an indomitable will","author":"Mahatma Gandhi"},"5":{"quote":"I think that the power is the principle. The principle of moving forward, as though you have the confidence to move forward, eventually gives you confidence when you look back and see what you’ve done","author":"Robert Downey Jr. (Former Addict)"},"6":{"quote":"If you accept the expectations of others, especially negative ones, then you never will change the outcome","author":"Frederick Douglass"},"7":{"quote":"I don't need alcohol to see the world in its depths, I carry the sun in me.","author":"Lamine Pearlheart"},"8":{"quote":"When you quit drinking, you stop waiting.","author":"Caroline Knapp"},"9":{"quote":"The day I became free of alcohol was the day that I fully understood and embraced the truth that I would not be giving anything up by not drinking.","author":"Liz Hemingway"},"10":{"quote":"Alcohol is a diuretic, it dehudrates the skin and reduces its elasticity. After stopping, colalgen levels are slowly restored and redness disappears","author":"Ashwood Recovery"},"11":{"quote":"Sobriety was the greatest gift I ever gave myself.","author":"Robert Lowe"},"12":{"quote":"Famous support group Alcoholics Anonymous teaches recovering adicts, through the 12-steps program, to take inventory and make amends with those they've hurt while drunk","author":"Ashwood Recovery"},"13":{"quote":"Alcohol contains almost double the caloric content than most proteins and carbs have (7 per gram) coming second only to fat itself (9 per gram)","author":"Ashwood Recovery"},"14":{"quote":"Several studies have shown that individuals who initially drink to sleep better but delve into abuse end up suffering severe cases of insomnia","author":"Ashwood Recovery"},"15":{"quote":"The less you drink, The less your Risk for Cancer is","author":"The CDC"}}

var myopathy_JSON = {"name":"Alcohol Myopathy","code":"G72.1","facts":{"0":"Acute alcoholic myopathy is present in 0.5 to 2.0 percent of alcoholics.","1":"Chronic alcohol-related myopathy is 10 times more common than the most common inherited myopathy (i.e. nemaline myopathy).","2":"Alcohol-related muscle disease is nearly 5 times more common than liver cirrhosis."}};
var liver_JSON = {"name":"Liver","code":"K70-K70.4, K70.9, K74.3-K74.6, K76.0, K76.9","facts":{"0":"In 2018, of the 83,517 liver disease deaths among individuals ages 12 and older, 47.8 percent involved alcohol.","1":"Fatty liver condition is present in approximately 90 to 100 percent of heavy drinkers.","2":"Alcohol remains the second most common cause of liver cirrhosis after hepatitis C virus (HCV) infection in the United States, contributing to approximately 20% to 25% cases of liver cirrhosis."}};
var epilepsy_JSON = {"name":"Epilepsy","code":"G40","facts":{"0":"Up to the point of about 24g of alcohol per day the curve between alcohol and the risk of epilepsy was relatively flat, after which, however, the risk curve increased steeply with increasing levels of consumption.","1":"A number of deaths are “sudden unexpected death in epilepsy” (SUDEP) where no cause of death is found at necropsy. Some of those are related to alcohol or are cases where the deceased previously have been admitted to hospital because of alcohol abuse, ranging from very few cases to 38% in different studies.","2":"If you drink in moderation you’re not likely to experience seizures, and you’re also probably not going to have withdrawal symptoms if you stop drinking, so you wouldn’t have to worry about experiencing withdrawal-related seizures."}};
