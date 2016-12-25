# Astergazer
## Contacts
Feel free to contact me via email: astergazer@ukr.net
## Description
Astergazer is a combined dialplan server, FastAGI server and visual dialplan constructor for the [Asterisk PBX](http://asterisk.org/). You can use it to build dialplan rules just using graphical interface building all call-routing logic from small blocks graphically, in a drag&drop style. That logic is keeped inside of database and it can be translated to native Asterisk dialplan automatically or on demand.
So, integration of Astergazer and Asterisk (see corresponding chapter) will let you ability to administer your PBX dialplan  in a WYSIWYG way. Besides, you will have an additional logic tools like *Checklist* block which lets you perform to check on-fly for existence of some expression value inside of predefined value list, *VoiceMenu* block which is kinda complex template of IVR level menu and so on.
The efficient using of that product (or at least primary configuration and integration with your Asterisk PBX) requires some basic knowledges about Asterisk configuration, dialplan functions and basisc of dialplan building. You can found Asterisk documentation [here](https://wiki.asterisk.org/wiki/display/AST/Home).
Despite this, when it's installed, integrated and configured, the barrier to entry to the dialplan configuration will be reduced so that any office manager is able to rule it.
Unfortunately, I haven't enough time to add complete and detailed help inside of it yet, but I hope to do it somedays. With a lot of features.

Interface supported languages: **English**, **Russian**.
## Dialplan server
Implemented as a regular http service, so you need to enable support of external includes to your Asterisk dialplan (see the **Integration** chapter).
## FastAGI server
Implemented using [Asterisk-Java](https://asterisk-java.org/) framework. You have to be sure that Asterisk has access to it's port (4573 by default).
## Installation
1. Install the [MySQL](https://www.mysql.com/) server.
2. Execute the `astergazer_mysql.sql` script
    ```sh
    cat astergazer_mysql.sql | mysql
    ```
3. Get the complete war package from this repository or build it manually using [Maven](https://maven.apache.org/).
4. Install the [Tomcat](http://tomcat.apache.org/) server.
5. Put the `mysql-connector-java-5.1.38.jar` from the repository to your Tomcat lib folder.
6. Put the `astergazer.war` to the /webapps Tomcat folder.
7. Start Tomcat.
8. If you deployed MySQL and Tomcat at different servers you have to accordingly change DB server path in the `<your Tomcat folder>/webapps/astergazer/META-INF/context.xml` and restart Tomcat service.

## Integration
The integration of Asterisk with Astergazer dialplan needs you to perform following steps:
1. Enable the parameter `execincludes=yes` in the `asterisk.conf`.
2. Add the exec directive to your `extensions.conf` at the end of file. Be sure that you have described context `[globals]` somewhere above that directive.	If you haven't or you in doubt about how to do it in a proper way, you can use the following example (just replace the content of your `extensions.conf` with it):
  ``` sh
	[globals]
	
	#exec wget -q -O - --proxy=off --dns-timeout=5 --connect-timeout=5 --read-timeout=5 127.0.0.1:8080/astergazer/translator
  ```
    where `127.0.0.1` is the IP address of the server where Astergazer is deployed. Use `127.0.0.1` if Asterisk and Astergazer are deployed at the same server.
3. Restart your Asterisk service. 

Now all contexts and extensions created in Astergazer will be automatically included to the dialplan. Notice, that any changes you made using Astergazer don't mean simultaneous changes in the Asterisk dialplan - you have to reload it manually by using CLI command 
``` sh
dialplan reload
```
 Or just add that command to the crontab schedule to avoid this trouble:
``` sh
* * * * * root /usr/sbin/asterisk -rx 'dialplan reload'
```
*(may depends of your Asterisk installation)*
 
## Using
#### Dialplan mapping

Using that interface window you can create the dialplan contexts and extensions just by filling the graphical dialplan tree. Also you can create the logic scripts contained call routing logic which can be assigned to any extensions.
Any logic script can be assigned to any number of different extensions. You can edit script logic by using the **Constructor** function. At this point of release you can't create `[globals]` context from that interface, so you need to have it in the native dialplan conf file (See the **Integration** chapter). To having that context is nessesary because of FastAGI logic which is used to some complex blocks like *CheckList* and *VoiceMenu*.

#### Script constructor
Here you can build some piece of call-routing logic by placing some command blocks and connecting them by logic arrows. Its pretty easy - just intuitive.
Some usefull features:
- You can create a new block by double click on such kind of it inside of blocks repository or just dragging it to the required place of the canvas.
- Copy-paste way is allowed too, by using context menu (right mouse button)
- You can move across the canvas holding left mouse button and moving the cursor
- You can zoom in or out just canvas working area by holding Shift key and using mouse wheel
- You can perform a frame multi selection by holding Shift key and left mouse button
- Also, you can select multiple blocks by holding Ctrl key and consequentially clicking on them by left mouse button

And another few notices.
- To apply any changes you made with some block, like name or any parameter changing you have to confirm it by pressing **Apply changes** button. It does't apply just by leaving that block and clicking at something else.
Maybe it will be changed in feature. At least I have few tickets about it already.
- Before you see how your script is looked in a translated mode, be sure that you saved it. Function **Translate** operates only actual version of current script contained in the DB.

#### Checklist management
That part of logic is used only for backround of the *CheckList* dialplan block.
You can create any number of lists like black, white, etc and fill it with any values which will be used for comparison with some expression value due to *CheckList* block using. If that value is found inside of specific list,
Astergazer means that script logic have to be passed through  the *True* sub-block, otherwise it will be the *False* one. Besides, if the value is matched to the list entry, Astergazer will set the dialplan variable `CHECKLIST_RETURN_VALUE`
with the value equals to **Return value** of that list record, so you can use it in some part of your logic, for instance as coverage path (call forwarding) for the certain internal number etc.

## Settings
A pretty easy chapter. Just one parameter have to be set here. FastAGI address means the IP of the server where is Astergazer deployed at, and that address must be reachable from the Asterisk server. Use `127.0.0.1` for the same machine.

## Compatibility
Actually, while developing that product I was guided to Asterisk version 11. However, the Astergazer architecture is builded especially as flexible as it possible, so it can easily be used with other Asterisk versions.
- Asterisk verison: **11**
- Java version : **8**
- MySQL version: **5.1**

***
Nice dialplan building!
Regards.
astergazer@ukr.net


	
	
