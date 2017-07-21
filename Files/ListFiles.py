import os,sys
def listdir(dir,file):
    file.write(dir + '\n')
    fielnum = 0
    list = os.listdir(dir) 
    for line in list:
        filepath = os.path.join(dir,line)
        if os.path.isdir(filepath): 
            myfile.write('   ' + line + '//'+'\n')
            for li in os.listdir(filepath):
                myfile.write('     '+ li + '\n')
                fielnum = fielnum + 1
        elif os.path:   
            myfile.write('   '+line + '\n')
            fielnum = fielnum + 1
    myfile.write('all the file num is '+ str(fielnum))
dir = raw_input('please input the path:')
myfile = open('list.txt','w')
listdir(dir,myfile)
myfile.close()