
#To link git with repo
>git remote add origin https://github.com/sukhadevpatil/books-rest-service

#validate remote link
>git remote -v

#add all file to git
>git add .

#if any error of email then set the email & name
>git config --global user.email "sukhadev.patil@gmail.com"
>git config --global user.name "Sukhdev Patil"

#commit code
>git commit -m 'base version of service code'

#push code to master branch
>git push --set-upstream origin master

