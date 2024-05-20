#include<stdio.h>
#include<string.h>

int main() {
    int i,j,k,l;
    char a[20], c[20], d[20];
    printf("RailFence");
    printf("Enter the string :");
    fgets(a, sizeof(a), stdin);
    a[strcspn(a,"\n")] ='\0';
    l=strlen(a);
    for (i =0,j=0;i<l;i+=2){
        c[j++] = a[i];
    }

    for (i=1;i<l;i+=2){
        c[j++] =a[i];
    }
    printf("Cipher text after railfence: ");
    printf("%s\n",c);

    if (l%2==0) k=(l/2);
    else k=(l/2)+1;
    for (i=0,j=0;i<k;i++) {d[j] =c[i];j+=2;}
    for (i=k,j=1;i<l;i++) {d[j] =c[i];j+=2;}
    
    printf("Text after decription : ");
    printf("%s\n",d);
    return 0;
}