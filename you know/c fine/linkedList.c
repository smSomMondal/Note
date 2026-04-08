#include <stdio.h>
#include <stdlib.h>

typedef struct node
{
    int a;
    struct node *next;
} node;
struct node *head = NULL;

void create(node *);
void delete(node *);
void view(node *);

void main()
{

    int n;
    while (1)
    {
        printf("enter \n 1->create \n 2->delete \n 3->view \n 0->exit \n enter your choise....");
        scanf("%d", &n);
        switch (n)
        {
        case 0:
            exit(0);
            break;

        case 1:
            create(head);
            break;

        case 2:
            delete(head);
            break;

        case 3:
            view(head);
            break;

        default:
            printf("chose any option....\n");
            break;
        }
    }
    printf("hi");
}
void create(node *temp)
{
    node *newNode = (node *)malloc(sizeof(node));
    printf("enter the value...");
    scanf("%d", &newNode->a);
    if (head == NULL)
    {
        newNode->next = NULL;
    }
    newNode->next = head;
    head = newNode;
};
void delete(node *temp)
{
    return;
};
void view(node *temp)
{
    if (temp == NULL)
    {
        printf("Linked List is empty...\n");
        return;
    }

    while (temp->next != NULL)
    {
        printf("%d--->", temp->a);
        temp = temp->next;
    }
    printf("%d--->NUll\n", temp->a);
};
