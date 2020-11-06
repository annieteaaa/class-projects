#include <stdio.h>
#include "converter.h"
#include <stdio.h>
#include <stdlib.h> 

void print_usage() {
    printf("You've entered an invalid flag.\n");
    printf("This program takes command line arguments and prints them in\n");
    printf("reverse order. Optionally, make your first command line\n");
    printf("the flag '-c' to reverse your arguments as well as capitalize\n");
    printf("all letters.\n");
}

int checkC(char *str1) {
    if(str1[0] == '-') {
        if(str1[1] == 'c') {
            if(str1[2] == '\0') {
                return 0;
            }
        }
        else {
            return 2;
        }
    }
    return 1;
}


void upper(char *str) {
    int i = 0;
    while(str[i] != '\0') {
        if(str[i] >= 97 && str[i] <= 122) {
            str[i] = str[i] - 32;
        }
	i++;
    }
}

int main(int argc, char **argv) {

    int end;
    int cap;
    int checkFlag;
    if(argc > 1) {
        checkFlag = checkC(argv[1]);
    }
    else if(argc == 1) {
           checkFlag = 1;
    }

    if(checkFlag == 0) {
        end = 2;
        cap = 1;
    }
    else if(checkFlag == 1) {
        end = 1;
        cap = 0;
    }
    else {
        print_usage();
    }

    int i = argc - 1;

    if(checkFlag != 2) {
    if(cap) {
        for (i; i >= end; i--) {
            upper(argv[i]);
	    if(i != end) {
                printf("%s ", argv[i]);
            }
	    else {
                printf("%s\n", argv[i]);
            }
        }
    }
    else {
        for (i; i >= end; i--) {
 	    if(i != end) {
                printf("%s ", argv[i]);
            }
	    else {
                printf("%s\n", argv[i]);
            }
        }
    }
    }
}

