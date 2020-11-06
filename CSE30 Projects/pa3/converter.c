#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include "converter.h"


int main(int argc, char **argv) {

	int opt;
	int cols;

	while ((opt = getopt(argc, argv, "+c:")) != -1) {
		switch(opt) {
			case 'c':
				cols = atoi(optarg);
				break;
			default:
				fprintf(stderr, "Usage is\n\t\%/.cnvtr -c input_column_count col# [col#...]\n");
				exit(EXIT_FAILURE);
		}
	}
	
	if(optind == argc) {
		fprintf(stderr, "Include column numbers to output.\n");
		exit(1);
	}

	long int temp;
	int ch;
	int orderlength = argc-optind;
	long int order[orderlength];
	int c = 0;

	while(optind < argc) {
		temp = strtol(argv[optind], NULL, 0);
		if(temp > cols || temp <= 0) {
			fprintf(stderr, "./cnvtr: dropping record# %d\n", optind);
			orderlength--;
		}
		else {
			order[c] = temp;
			c++;
		}
		optind++;
	}


	while(ch != EOF) {
		char line[cols][1024];
		int count = 0;
		for(count; count < cols; count++) {
			int quotes = 0;
			ch = fgetc(stdin);
			if(ch == 34) {
				quotes = 1;
			}
			int num = 0;
			while(ch != EOF && ch != '\n' && (quotes == 1 || ch != ',')) {
				line[count][num] = ch;
				num++;
				ch = fgetc(stdin);
				if(ch == 34) {
					quotes = 0;
				}
			}
			line[count][num] = '\0';
		}

		if(ch != EOF) {
			count = 0;
			for(count; count < orderlength; count++) {
				int num = 0;
				char ex = line[order[count]-1][num];
				while(ex != '\0') {
					printf("%c", ex);
					num++;
					ex = line[order[count]-1][num];
				}
				if(count != orderlength-1) {
					printf(",");
				}
				else {
					printf("\n");
				}
			}
		}
	}

}
