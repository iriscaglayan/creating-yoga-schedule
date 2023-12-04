# My Personal Project


## *Iris Caglayan personal project CPCS 210*

This project aims to create an application for myself to keep track of my yoga budget and yoga schedule for each week:
1. Which days of the week and times of the day which types of yoga classes that I will go?
2. What is my budget for that week?
3. What is my remaining budget after I signed up to classes
4. Is my money sufficient enough?

The particular yoga studio I am creating this app has class schedules that people can sign up to. 
Customers pay money per class. In each class customers participate in different types of yoga practices.
Therefore, **tracking which days and times and which type of yoga is preferred will allow this yoga studio to 
create a class schedule that would attract more customers**. Furthermore, **the music preferences of the customers for
specific yoga types would increase the quality and satisfaction of the yoga class**.
The last information which is reporting the customer's aim to participate in yoga
would **be used in written descriptions of each yoga class on yoga studio's social media and 
customers could choose the type of yoga they want to attend based on their aim**, 
so customers can leave the yoga studio more satisfied.
Moreover, if a pattern such as people tend to choose *Chill* yoga classes on Monday mornings is detected,
the schedules could be adapted better and different practices like deep breathing exercises could be added to the class
to increase customer satisfaction.

This project has a personal relevance to me because as someone who have been dealing with high stress and anxiety,
*yoga helped me stay at the moment and be calm*. Therefore, yoga increased my life quality during both on daily basis
and the times that I struggled with depression. I started going to a yoga studio which changed my whole experience with
college and classes I am taking, I learned how to stay calm, not worry about future and stay at the moment. However,
sometimes I tend to *spend more than my budget*, and *go to same type of yoga class over and over again*
 **I wanted to create an app that would help me plan my yoga budget daily and have a yoga schedule that I will stick 
through the week.**.

## User Stories

-As a user, I want to be able to create a new yoga class,
-As a user I want to type the day, time, type of the yoga class that I want to attend 
-As a user, I want to be able to add my budget for that day
-As a user, I want to be able to deduct the cost of each yoga class from my budget
-As a user, I want to be able to view my yoga schedule for that day
-As a user, I want to save my yoga class list and have the select option
-As a user, I want to able to load my yoga list with their costs.

## "Instructions for Grader"
- You can generate the first required action related to deducing cost from budget by entering the date after clicking 
addclas frame.
- You can type in data to create a yoga class through addclass frame.
- You can remove a yogaClass by typing the data about the class you want to delete
- Removing a yoga class will update your budget and totalcost
- You can add your budget on the addclass frame.
- You can also generate the second required action related to adding Xs to a Y by clicking confirm where you can see your 
total cost of yogaClasses.
- You can locate my visual component by opening the loadFrame and clicking to loadSchedule button on the frame.
- You can save the state of my application by clicking save schedule button on AddClass frame.
- You can reload the state of my application by clicking loadschedule frame and loadschedule button on that frame.

##Phase 4: Task 2
Representative sample of event that occurs:
"YogaClass is created"
"Yoga Class is removed"
"Yoga Schedule is printed"
"Total yoga cost is printed"

##Phase 4: Task 3

Refactoring that can be done to improve the design of my program:

-To decrease coupling loadFrame and addClassFrame can be merged together. 
This may also increase user experience as well because loading schedule would be more accesible.

-YogaClass also implements Writable this can also be limited only schedule implementing the writable to decrease coupling.

-There are a lot of code repetitions in addClassFrame, 
this can be managed by adding more methods to schedule and yogaClass.
-AddClassFrame's association can be decreased to only with schedule.





 