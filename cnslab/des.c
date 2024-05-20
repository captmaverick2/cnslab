#include<stdio.h>

int ip[] = {2,6,3,1,4,8,5,7};
int ip_inv[] = {4,1,3,5,7,2,8,6};

int so[4][4] ={{1,0,3,2},{3,2,1,0},{0,2,1,3},{3,1,3,2}};
int s1[4][4] = {{0,1,2,3},{2,0,1,3},{3,0,1,0},{2,1,0,3}};

int initial(int plaintext) {
    int res =0;
    for (int i=0;i<8;i++){
        res |= ((plaintext>>(8-ip[i]))&1)<<(7-i);
    }
    return res;
}

int initial_inv(int ciphertext){
    int res =0;
    for(int i=0;i<8;i++){
        res |= ((ciphertext>>(8-ip_inv[i]))&1)<<(7-i);
    }
    return res;
}

int s_box_subs(int value, int s_box[4][4]){
    int row = ((value & 0b1000)>>2)|(value & 0b0001);
    int col = (value &0b0110)>>1;
    return s_box[row][col];
}

int main(){
    int plaintext = 0b11010110;
    printf("Plain text : %x\n", plaintext);
    int ciphertext = initial(plaintext);
    printf("Cipher text : %x\n", ciphertext);

    int s_box_value = 0b1101;

    int s_box_Res = s_box_subs(s_box_value, so);

    printf("S box result : %x\n", s_box_Res);
    int dec_text = initial_inv(ciphertext);
    printf("Decrypted text : %x\n", dec_text);
    return 0;
}