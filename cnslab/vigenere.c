#include<stdio.h>
// #include<typeinfo.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>


void encrypt(){
    char plaintext[128] ,key[16];
    printf("\nEnter plain text : ");
    scanf(" %[^\n]",plaintext);

    printf("Enter the key: ");
    scanf(" %[^\n]", key);

    printf("Cipher text : ");
    for (int i=0,j=0;i<strlen(plaintext);i++,j++){
        if (j>=strlen(key)) j=0;
        int shift = toupper(key[j])-'A';
        char encrypted = ((toupper(plaintext[i]) -'A' + shift)%26)+'A';
        printf("%c", encrypted);
    }
}

void decrypt(){
    char ciphertext[128], key[16];
    printf("\nEnter cipher text : ");
    scanf(" %[^\n]",ciphertext);
    printf("Enter key: ");
    scanf(" %[^\n]", key);
    printf("Deciphered text: ");
    for (int i=0,j=0;i<strlen(ciphertext);i++,j++){
        if (j>=strlen(key)) j=0;
        int shift = toupper(key[j])-'A';
        char decrypted  = ((toupper(ciphertext[i])-'A'-shift + 26)%26)+'A';
        printf("%c", decrypted);
    }
}

int main() {
    int choice;
    while (1) {
        printf("\n1.encrypt\n2.decrypt\n3.exit\nChoice : ");
        scanf("%d", &choice);
        switch (choice) {
            case 1: encrypt(); break;
            case 2: decrypt();break;
            case 3: exit(0);
            default:
            printf("Enter valid choice");
            break;
        }
    }
    return 0;
}