#include <stdio.h>
#include <conio.h>
#include <math.h>
#include<string.h>

#define max(a, b) ((a) > (b) ? (a) : (b))

int knapSack(int *, int *, int, int);
int knapSackMemor(int *, int *, int, int);
int knapSackNotReqers(int *, int *, int, int);

int mem[100][100];
void main()
{
    int waight[] = {10, 20, 15, 5, 7};
    int value[] = {100, 58, 40, 30, 150};
    int sakCap = 20,siz=sizeof(waight)/4,n;

    memset(mem,-1,sizeof(mem));

    n=knapSack(waight,value,sakCap,siz);
    printf("max profit = %d\n",n);

    knapSackMemor(waight,value,sakCap,siz);
    printf("max profit = %d",mem[sakCap][siz]);

    knapSackNotReqers(waight,value,sakCap,siz);
}
int knapSack(int *w, int *v, int saCa, int siz)
{
    if (saCa == 0 || siz == 0)
    {
        return 0;
    }

    if (w[siz - 1] <= saCa)
    {
        return max( v[siz-1] + knapSack(w, v, saCa - w[siz - 1], siz - 1), knapSack(w, v, saCa , siz - 1));
    }else{
        return knapSack(w, v, saCa, siz - 1);
    }
}

/*memorizing technik*/

int knapSackMemor(int *w, int *v, int saCa, int siz)
{
    /*base condition*/
    if (saCa == 0 || siz == 0)
    {
        return 0;
    }

    /**/
    if (mem[saCa][siz]!=-1)
    {
        return mem[saCa][siz];
    }
    

    if (w[siz - 1] <= saCa)
    {
        return mem[saCa][siz]=max( v[siz-1] + knapSackMemor(w, v, saCa - w[siz - 1], siz - 1), knapSackMemor(w, v, saCa , siz - 1));
    }else{
        return mem[saCa][siz]=knapSackMemor(w, v, saCa, siz - 1);
    }
}

int knapSackNotReqers(int *w, int *v, int saCa, int siz){
    int dp[siz+1][saCa+1];
    for (int i = 0; i < siz+1; i++)
    {
        for (int j = 0; j < saCa+1; j++)
        {
            if(i==0 || j==0){
                dp[i][j]=0;
            }
        }
        
    }

    for (int i = 1; i < siz+1; i++)
    {
        for (int j = 1; j < saCa+1; j++)
        {
            if (w[i - 1] <= j)
            {
                dp[i][j]=max(v[i-1]+dp[i-1][j-w[i-1]],dp[i-1][j]);
            }else{
                dp[i][j]=dp[i-1][j];
            }
            
        }
        
    }

    printf("larjest value %d",dp[siz][saCa]);
    
}